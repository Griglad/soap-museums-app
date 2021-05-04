package com.soap.services;

import com.soap.jpa.DbMuseum;
import com.soap.model.FindNamesLargerInputValueRequest;
import com.soap.model.FindNamesLargerInputValueResponse;
import com.soap.utilities.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Grigorios Ladas
 */
@Service
public class FindMuseumsNamesLargerInputService {

    private final Logger logger = LoggerFactory.getLogger(FindMuseumsNamesLargerInputService.class);


    //Returning a response with a list of museums which their request counter is larger than the input value
    public FindNamesLargerInputValueResponse getPointsLargerResponse(@RequestPayload FindNamesLargerInputValueRequest request) {
        FindNamesLargerInputValueResponse response = new FindNamesLargerInputValueResponse();
        Set<DbMuseum> dbMuseumSet = FindNearestMuseumNameService.getDbMuseumSet();
        long value = request.getCounterValue();
        List<DbMuseum> largerThanCounterVal = dbMuseumSet.stream().filter(e -> e.getCounter() > value).collect(Collectors.toList());
        List<String> museumNames = largerThanCounterVal.stream().map(DbMuseum::getName).collect(Collectors.toList());
        logger.info("museum names larger than " + request.getCounterValue() + " counter value are " + museumNames);
        if (!museumNames.isEmpty()) {
            response.setMessage(Messages.LARGER_VALUE_FOUND.info + value);
            response.getNames().addAll(museumNames);
        } else {
            response.setMessage(Messages.LARGER_VALUE_NOT_FOUND.info + value);

        }
        return response;
    }


}
