package project.controller;

import org.json.JSONObject;
import project.service.EtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private EtlService etlService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/turn{turn:on|off}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String start(
            @PathVariable(
                    required = true
            ) String turn
    ) {
        if (turn.equalsIgnoreCase("on")) {
            etlService.start();
        } else {
            etlService.stop();
        }
        JSONObject response = new JSONObject();
        response.put("turned", turn);
        return response.toString();
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String info() {
        JSONObject response = new JSONObject();
        response.put("state", etlService.loadCurrentState());
        response.put("completePercent", etlService.loadCompletePercent());
        response.put("isExecuting", etlService.isExecuting());
        response.put("isStoping", etlService.isStoping());
        return response.toString();
    }

}
