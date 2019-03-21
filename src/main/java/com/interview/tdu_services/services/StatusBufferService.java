package com.interview.tdu_services.services;

import com.google.gson.Gson;
import com.interview.tdu_services.Application;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public class StatusBufferService{
    @GET
    @Path("/buffer")
    @Produces({"application/xml","application/json"})
    public Response getStatusBuffer(){
        if (!Application.isRunning()){
            Application.run();
        }

        String[] arrayData = Application.getBuffer().getArrayData();
        Gson gson = new Gson();
        gson.toJson(arrayData);
        return Response.ok(arrayData).build();//???
    }

}
