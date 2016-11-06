package br.com.brunotonia.rest.ws;

import br.com.brunotonia.rest.dao.CarroDAO;
import br.com.brunotonia.rest.vo.Carro;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/carro")
public class CarroWS {
    @GET
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String testes() {
        return "http://localhost:8080/CD_REST_Servidor/dados/carro/ \n"
                + "http://localhost:8080/CD_REST_Servidor/dados/carro/{id} - exibe o json do Carro com o id desejado \n"
                + "http://localhost:8080/CD_REST_Servidor/dados/carro/adicionar/{json} - adiciona o Carro enviado por json \n"
                + "http://localhost:8080/CD_REST_Servidor/dados/carro/listar - exibe o json da lista de carros";
    }

    @Path("/adicionar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String adicionar(Carro carro) {
        boolean b = new CarroDAO().adicionar(carro);
        System.out.print("b = " + b);
        if (b == true) {
            return "ok";
        } else {
            return "fail";
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Carro buscar(@PathParam("id") Long id) throws Exception {
        Carro carro = new CarroDAO().selecionar(id);
        return carro;
    }

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() throws Exception {
        Carro[] carros = new CarroDAO().listar();
        return new Gson().toJson(carros);
    }

}
