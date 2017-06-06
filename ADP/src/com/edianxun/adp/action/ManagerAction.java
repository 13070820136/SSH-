package com.edianxun.adp.action;
/**
 * @author lhr
 * @date 2014-4-30
 */

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.edianxun.adp.pojo.Manager;
import com.edianxun.adp.service.ManagerService;
import com.opensymphony.xwork2.ModelDriven;

@Results({@Result(name = "main", location = "/jsp/main.jsp", type = "redirect")})
public class ManagerAction extends BaseAction
        implements ModelDriven<Manager> {

    private static final long serialVersionUID = -1040088992400395429L;
    private static final Logger logger = Logger.getLogger(ManagerAction.class);
    private Manager manager = new Manager();
    private ManagerService managerService;
    private String name;

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    public String execute() {
        logger.debug("bingo");
        return SUCCESS;
    }

    @Action("mgrlogin")
    public String login() {
        Map<String, Object> session = getSession();
        try {
            int managerId = managerService.loginValidate(manager);
            logger.debug("manager Id :" + managerId);
            if (managerId > 0) {
                session.put("managerId", managerId);
                session.put("manager", manager.getName());
            } else {
                return LOGIN;
            }
        } catch (Exception e) {
            logger.debug("manager login exception");
            e.printStackTrace();
        }
        return "main";
    }

    @Action("mgrlogout")
    public String logout() {
        logger.debug("logout");
        Map<String, Object> session = getSession();
        session.remove("managerId");
        session.remove("manager");
        return null;
    }

    @Action("mgrlist")
    public void list() {
        logger.debug("query all manager");
        try {
            this.outJson(managerService.managerList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action("mgradd")
    public void add() {
        try {
            manager.setStatus("NORMAL");
            manager.setCreateTime((int) new Date().getTime());
            int managerId = managerService.addManager(manager);
            logger.debug("managerId : " + managerId);
            if (managerId <= 0) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action("mgrupdate")
    public void update() {
        logger.debug("manager update password ...");
        try {
            Map<String, Object> session = getSession();
            int id = (Integer) session.get("managerId");
            Manager man = managerService.getManager(id);
            man.setPassword(manager.getPassword());
            managerService.update(man);
            this.outJson(man.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action("mgrUpdateInfo")
    public void updateInfo() {
        logger.debug("manager update info ...");
        try {
            System.out.println("####" + manager.getDescrition());
            managerService.update(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Action("mgrdel")
    public void del() {
        logger.debug("manager delete");
        try {
            managerService.del(manager.getManagerId());
            this.outJson("ok");
        } catch (Exception e) {
            e.printStackTrace();
            this.outJson("nok");
        }
    }

    @Action("gatelist")
    public void gatelist() {
        logger.debug("query all manager");
        try {
            this.outJson(managerService.gateList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Manager getModel() {
        return manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
