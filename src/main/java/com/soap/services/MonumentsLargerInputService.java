package com.soap.services;

import com.soap.jpa.DbMonument;
import com.soap.model.GetNamesLargerInputValueRequest;
import com.soap.model.GetNamesLargerInputValueResponse;
import com.soap.utilities.Messages;
import com.soap.utilities.MonumentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grigorios Ladas
 */
@Service
public class MonumentsLargerInputService {


    private Logger logger = LoggerFactory.getLogger(MonumentsLargerInputService.class);


    //Returning a response with a list of monuments which their request counter is larger than the input value
    public GetNamesLargerInputValueResponse getPointsLargerResponse(@RequestPayload GetNamesLargerInputValueRequest request) {
        GetNamesLargerInputValueResponse response = new GetNamesLargerInputValueResponse();
        long value = request.getCounterValue();
        List<DbMonument> largerThanCounterVal = MonumentUtil.getMonuments().stream().filter(e -> e.getCounter() > value).collect(Collectors.toList());
        List<String> monumentNames = largerThanCounterVal.stream().map(DbMonument::getName).collect(Collectors.toList());
        logger.info("monument names larger than " + request.getCounterValue() + " counter value are " + monumentNames);
        if (!monumentNames.isEmpty()) {
            response.setMessage(Messages.LARGER_VALUE_FOUND.info + value);
            response.getNames().addAll(monumentNames);
        } else {
            response.setMessage(Messages.LARGER_VALUE_NOT_FOUND.info + value);

        }
        return response;
    }


}
