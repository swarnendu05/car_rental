package com.realProject.controller.CRMCntroller;




import com.realProject.entity.Evaluation.Agent;

import com.realProject.entity.Evaluation.Area;
import com.realProject.entity.Evaluation.CoustomerVisit;
import com.realProject.repository.AgentRepository;
import com.realProject.repository.AreaRepository;
import com.realProject.repository.CoustomerVisitRepository;
import com.realProject.service.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {
    private AreaRepository areaRepository;
    private AgentRepository agentRepository;
    private CoustomerVisitRepository coustomerVisitRepository;
    private SmsService smsService;


    public AreaRepository getAreaRepository() {
        return areaRepository;
    }

    public void setAreaRepository(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public SmsService getSmsService() {
        return smsService;
    }

    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    public CoustomerVisitRepository getCoustomerVisitRepository() {
        return coustomerVisitRepository;
    }

    public void setCoustomerVisitRepository(CoustomerVisitRepository coustomerVisitRepository) {
        this.coustomerVisitRepository = coustomerVisitRepository;
    }

    public AgentRepository getAgentRepository() {
        return agentRepository;
    }

    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Area>> searchAgent(
            @RequestParam String pinCode
    ) {

        System.out.println("Received pinCode: " + pinCode); // Debugging log
        List<Area> areas = areaRepository.findByPinCode(pinCode);

        if (areas.isEmpty()) {
            System.out.println("No areas found for pinCode: " + pinCode);
        }

        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    //update the agent details in database
//    @PutMapping
//    public String allocateAgent(
//            @RequestParam long customerId,
//            @RequestParam long agentId
//    ) {
//
//        Agent agent = null;
//        Optional<Agent> opAgent = agentRepository.findById(agentId);
//        if(opAgent.isPresent()) {
//            agent = opAgent.get();
//
//        }
//        CoustomerVisit customerVisit = coustomerVisitRepository.findById(customerId)
//                                       .get();
//        customerVisit.setAgent(agent);
//
//        coustomerVisitRepository.save(customerVisit);
//
//        smsService.sendSms("+917718380729","+917718380729 hi swarnendu");
//        return "Agent is now Allocated";
//
//    }

}
