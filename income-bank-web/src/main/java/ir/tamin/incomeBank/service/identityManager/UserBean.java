package ir.tamin.incomeBank.service.identityManager;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.model.baseinfo.Branch;
import ir.tamin.portal.model.org.User;
import ir.tamin.portal.service.implementation.UserClientService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author A_Jamali
 */
@ApplicationScoped
public class UserBean {


    @Inject
    private UserClientService userClientService;
    private final Map<String, User> userMap = new HashMap<>();
    private final Map<String, User> usernameToUserMap = new HashMap<>();

    public User getUser(String entityId) throws Exception {
        if (userMap.containsKey(entityId)) {
            return userMap.get(entityId);
        }
        User user = userClientService.get(entityId);
        userMap.put(user.getEntityId(), user);
        usernameToUserMap.put(user.getLogin(), user);
        return user;
    }

    public User getUserByUserName(String username) throws Exception {
        if (usernameToUserMap.containsKey(username)) {
            return usernameToUserMap.get(username);
        }
        User user = userClientService.getByUserName(username);
        userMap.put(user.getEntityId(), user);
        usernameToUserMap.put(user.getLogin(), user);
        return user;
    }

    public Map<String, Object> getUsers(FilterWrapper fw, Integer start, Integer limit) throws Exception {
        Map resultMap = userClientService.getAll(fw, start, limit, null, null);
        Iterator it = ((List<User>) resultMap.get("list")).iterator();
        while (it.hasNext()) {
            User user = (User) it.next();
            userMap.put(user.getEntityId(), user);
        }
        return resultMap;
    }

    public User reloadUser(String username) throws Exception {
        User user = this.userClientService.getByUserName(username);
        this.userMap.put(user.getEntityId(), user);
        this.usernameToUserMap.put(user.getLogin(), user);
        return user;
    }


    public boolean isEdareKol(User user) throws Exception {
        if (user.getNationalCode() == null) {
            user = userClientService.getByUserName(user.getNationalCode());
            if (user != null && user.getNationalCode() != null) {
                userMap.put(user.getNationalCode(), user);
            }
            String userOIMORGCode = user.getOrganization().getCode();
            Branch branch = getEntityManager().find(Branch.class, userOIMORGCode);
            return branch.getBrhKind().equals("2");
        }
        return false;
    }


    @Inject
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
