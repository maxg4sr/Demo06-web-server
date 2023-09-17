package tw.hp.demo06.web.server.pojo.entity;

import lombok.Data;

/**
 * @ClassName SocketMsg
 * @Date 2021/9/21 14:10
 **/
@Data
public class SocketMsg {

    private int type; //聊天类型0：群聊，1：单聊.
    private String fromUser;//发送者.
    private String toUser;//接受者.
    private String msg;//消息

}

