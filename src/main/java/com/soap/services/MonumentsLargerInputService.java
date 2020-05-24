package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.model.GetNamesLargerInputValueRequest;
import com.soap.model.GetNamesLargerInputValueResponse;
import com.soap.model.Monument;
import com.soap.utilities.MonumentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonumentsLargerInputService {


    private MonumentDao monumentDao;
    private Logger logger = LoggerFactory.getLogger(MonumentsLargerInputService.class);

    @Autowired
    public MonumentsLargerInputService(MonumentDao monumentDao) {
        this.monumentDao = monumentDao;
    }


    //returning a response with a list of monuments  which their counter is larger than the input value
    public GetNamesLargerInputValueResponse getPointsLargerResponse(@RequestPayload GetNamesLargerInputValueRequest request) {
        GetNamesLargerInputValueResponse response = new GetNamesLargerInputValueResponse();
        List<Monument> dbMonumentsLargerThan = MonumentUtil.getMonuments().stream().filter(e -> e.getCounter() > request.getPoint()).collect(Collectors.toList());
        List<String> monumentNames = dbMonumentsLargerThan.stream().map(e -> e.getName()).collect(Collectors.toList());
        logger.info("monument names larger than " + request.getPoint() + " are " + monumentNames);
        response.getNames().addAll(monumentNames);

        return response;

    }


}
