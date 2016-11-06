package br.com.brunotonia.rest.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensagem {
    
    boolean msg;

    public Mensagem() {
        this.msg = false;
    }

    public Mensagem(boolean msg) {
        this.msg = msg;
    }
    
    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }
    
}
