package com.exer.labseq.resource;

import com.exer.Paths;
import com.exer.labseq.Labseq;
import com.exer.labseq.dto.LabseqDTO;

import io.smallrye.common.constraint.NotNull;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path(Paths.BASE_URL)
//"application/json" or MediaType.APPLICATION_JSON
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LabseqResource {

     @Inject
     Labseq labseq;
     @GET()
     @Path(Paths.SEQ_URL)
     @Operation(description = "Makes a request to the Labseq class to get cached n'th value or compute a n'th value.", summary = "Calculates the labseq value for the given n'th position." )
     @APIResponses(value = {
             @APIResponse(responseCode = "200", description = "Labseq value successfully retrieved.",
                     content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LabseqDTO.class))),
             @APIResponse(responseCode = "400", description = "Bad Request, The requested value is not valid.",
                     content = @Content(mediaType = MediaType.APPLICATION_JSON))
     })
     public Response getLabseq(@PathParam("n") @NotNull @Parameter(description = "Index of the Labseq sequence. Only the value of that index will be returned") final Integer n){
          Response response;

          if(n<0){ //negative index numbers are not allowed == bad request
               response = Response.status(400, "Bad Request, " + n.toString() + " is not a valid index integer! Needs to be a non-negative number.").build();
          }else{
               response = Response.ok(LabseqDTO.of(labseq.getLabseqIn(n))).build();
          }
          return response;
     }
}
