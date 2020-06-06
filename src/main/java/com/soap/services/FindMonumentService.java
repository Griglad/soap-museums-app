package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.FindMonumentRequest;
import com.soap.model.FindMonumentResponse;
import com.soap.model.Monument;
import com.soap.utilities.Messages;
import com.soap.utilities.MonumentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Grigorios Ladas
 */
@Service
public class FindMonumentService {


    private final MonumentDao monumentDao;

    @Autowired
    public FindMonumentService(MonumentDao monumentDao) {
        this.monumentDao = monumentDao;
    }

    public FindMonumentResponse findMonumentResponse(@RequestPayload FindMonumentRequest request) {

        FindMonumentResponse findMonumentResponse = new FindMonumentResponse();
        String monumentName = request.getName().toLowerCase().trim();

        DbMonument dbMonument = monumentDao.findMonument(monumentName);

        if (dbMonument != null) {
            findMonumentResponse.setMessage(Messages.MONUMENT_FOUND.info);
            Monument monument = MonumentUtil.fromdbToMonument(dbMonument);
            findMonumentResponse.setMonument(monument);
            return findMonumentResponse;
        } else {
            findMonumentResponse.setMessage(Messages.MONUMENT_NOT_FOUND.info);

            return findMonumentResponse;
        }


    }
}
