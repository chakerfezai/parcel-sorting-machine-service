package org.sciam.kogito.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sciam.kogito.model.Container;
import org.sciam.kogito.model.Item;
import org.sciam.kogito.model.Receptacle;

@Path("/parcel")
@ApplicationScoped
public class AppRessource {

    @GET
    @Path("/item")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllItem() {
        return Response.ok(Item.listAll()).build();
    }

    @GET
    @Path("/item/{rec}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllItemByReceptacleID(@PathParam("rec") long rec) {
        return Response.ok(Item.list("receptacleId", rec)).build();
    }

    @GET
    @Path("/receptacle")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllRec() {
        return Response.ok(Receptacle.listAll()).build();
    }

    @GET
    @Path("/container")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllContainer() {
        return Response.ok(Container.listAll()).build();
    }

    @GET
    @Path("/container/open/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response existOpenedContainer(@PathParam("id") long receptacleId) {
        Receptacle r = Receptacle.findById(receptacleId);
        var result = false;
        if (r != null) {
            var c = Container.find("destination = ?1 and status = 'OPENED' ", r.destination).firstResult();
            if (c != null) {
                result = true;
            }
        }
        return Response.ok(result).build();

    }
}
