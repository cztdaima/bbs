package com.dhu.bbs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * author :钱国正
 *
 */

public class Client extends JFrame implements ActionListener,ItemListener,ListSelectionListener,Runnable{

    public static int selectednumber=0;
    private static final int PORT = 8888;
    Thread thread1=new Thread(this);  //私聊
    Thread thread2=new Thread(this);  //群聊

    JPanel chatText=null;
    JPanel toolbar=null;
    TextArea showMsg=null;
    TextArea writeMsg=null;
    JList listview=null;
    JCheckBox checkBox=null;
    JCheckBox checkBox1=null;
    JLabel chatroom=null;
    JButton send=null;
    JButton shutdown=null;
    JTextField  ipaddress=null;
    DefaultListModel listModel;
    public Client()
    {
        super("ChatRoom");
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        send=new JButton("Send");
        shutdown=new JButton("Shutdown");
        showMsg=new TextArea("",10,60,TextArea.SCROLLBARS_BOTH);
        writeMsg=new TextArea("",6,60,TextArea.SCROLLBARS_VERTICAL_ONLY);
        listModel=new DefaultListModel();
        setBounds(150, 150, 600, 510);
        //listview.setFixedCellHeight(250);
        //listview.setFixedCellWidth(200);
        JScrollPane listScrollPane=new JScrollPane(listview);

        //listname=new JLabel("User name:");

        listview=new JList();
        chatText=new JPanel();
        //list=new JPanel();
        toolbar=new JPanel();
        chatroom=new JLabel("钱哥哥聊天室(默认聊天方式：群聊)");
        JLabel label=new JLabel("私聊 IP地址:");
        checkBox=new JCheckBox("私聊");
        checkBox1=new JCheckBox("群聊");
        //checkBox1.setSelected(true);
        ipaddress=new JTextField(11);
        toolbar.add(label);
        toolbar.add(ipaddress);
        toolbar.add(checkBox);
        toolbar.add(checkBox1);
        chatText.add(chatroom);
        chatText.add(showMsg);
        chatText.add(toolbar);
        chatText.add(writeMsg);
        chatText.add(shutdown);
        chatText.add(send);
        //chatText.add(checkBox,BorderLayout.WEST);
        //添加侦听事件：
        send.addActionListener(this);
        shutdown.addActionListener(this);
        listview.addListSelectionListener(this);
        checkBox.addItemListener(this);
        checkBox.setSelected(false);
        //list.add(checkBox);
        showMsg.setEditable(false);
        showMsg.setBackground(Color.gray);
        showMsg.setFont(new Font("楷体", Font.PLAIN, 14));
        writeMsg.setFont(new Font("楷体", Font.PLAIN, 14));
        writeMsg.setBackground(Color.orange);
        chatText.setBackground(Color.cyan);
        listview.setBackground(Color.DARK_GRAY);
        listview.setVisibleRowCount(8);
        listview.setVisible(true);
        listview.setBounds(20,20,80,120);
        listview.setLayout(null);
        listview.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JList list=(JList)e.getSource();
                if(e.getClickCount()==2){
                    int index=list.locationToIndex(e.getPoint());
                    //UserEntry user = (UserEntry) list.getModel().getElementAt(index);
                    //JOptionPane.showMessageDialog(null, "you double clicked ");
                    //new QQFrame();
                }
            }
        });
        Container content=getContentPane();
        content.setBackground(Color.GREEN);
        content.add(chatText);
        //content.add(listview);
        content.setLayout(new GridLayout());
        setVisible(true);
        setSize(540,440);
        this.setExtendedState(0);
        this.setAlwaysOnTop(true);
        this.setFocusable(true);
        this.setMaximumSize(this.getSize());
        this.setMinimumSize(this.getSize());
        thread2.start();
        //QQ的通信模块

    }
    public void sendPrivateData(String msg){
        //InetAddress host = null;
        InetAddress targetip=null;
        try {
            if(!isIPAddress(ipaddress.getText().toString())){
                JOptionPane.showMessageDialog(this, "对不起！请输入正确的IP地址", "错误",JOptionPane.ERROR_MESSAGE );
                return;
            }
            targetip = InetAddress.getByName(ipaddress.getText().toString());
            String ip=InetAddress.getLocalHost().getHostAddress();
            Date date=new Date();
            byte[] buf=msg.getBytes();
            //创建发送数据包并指定接收主机为本地主机，接收进城的接收端口为1990
            DatagramPacket sendPacket=new DatagramPacket(buf,buf.length,targetip,1990);
            DatagramSocket socket=new DatagramSocket();
            String s="\n"+"【"+ip+"】"+((int)date.getMonth()+1)+"月"+date.getDate()
                    +"日   "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n";
            //sb.append(packer);
            showMsg.append(s+msg);
            socket.send(sendPacket);
            writeMsg.setText("");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public  void sendPublicData(String msg){
        try {
            String ip=InetAddress.getLocalHost().getHostAddress();
            Date date=new Date();
            DatagramSocket sendSocket=new DatagramSocket();
            byte[] bytebuf=new byte[1024*4];
            bytebuf=msg.getBytes();
            DatagramPacket packet=new DatagramPacket(bytebuf,bytebuf.length,
                    InetAddress.getByName("225.8.8.8"),2010);
            String s="/n"+"【"+ip+"】"+((int)date.getMonth()+1)+"月"
                    +date.getDate()+"日   "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n";
            showMsg.append(s+msg);
            sendSocket.send(packet);
            writeMsg.setText("");
            sendSocket.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        new Client();
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        Object obj=e.getItem();
        if(obj.equals(checkBox)){
            if(ipaddress.getText().isEmpty()){
                String ipaddr=JOptionPane.showInputDialog("请输入私聊IP地址！");
                ipaddress.setText(ipaddr);
                while(!isIPAddress(ipaddress.getText().toString())){
                    ipaddr=JOptionPane.showInputDialog("请输入正确私聊IP地址！");
                    ipaddress.setText(ipaddr);
                }
            }
            if(checkBox.isSelected()){
                if(thread2.isAlive()){
                    thread2.interrupt();
                }
                if(!thread1.isAlive()){
                    thread1=new Thread(this);
                }
                try{
                    thread1.start();
                }catch(Exception ex){

                }
            }
        }else if(obj.equals(checkBox1)){
            thread1.interrupt();
            if(checkBox1.isSelected()){
                if(!thread2.isAlive()){
                    thread2=new Thread(this);
                }
                try{
                    thread2.start();
                }catch(Exception ex){

                }
            }
        }
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub
        if(e.getValueIsAdjusting()==false){
            if(listview.getSelectedIndex()==-1){
                //JOptionPane.showInputDialog("请选择私聊对象");
            }else{
                selectednumber=listview.getSelectedIndex();
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand().equals("Send")){
            //sendPublicData(writeMsg.getText().toString());
            if(writeMsg.getText().isEmpty()==true||writeMsg.getText().toString()==""){

                JOptionPane.showMessageDialog(this,"对不起！请您输入聊天内容！","提醒",
                        JOptionPane.WARNING_MESSAGE);
            }else{
                //sendPrivateData(writeMsg.getText().toString());
                sendPublicData(writeMsg.getText().toString());
            }
            writeMsg.setText("");

        }
        if(e.getActionCommand().equals("Shutdown")){
            System.exit(0);
        }
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        if(Thread.currentThread()==thread2){//群聊
            try{
                //DatagramSocket receive=new DatagramSocket(1990);
                MulticastSocket receive=new MulticastSocket(2010);
                InetAddress multicastIP=InetAddress.getByName("225.8.8.8");
                receive.joinGroup(multicastIP);
                byte[] data=new byte[1024*4];
                DatagramPacket receivepacket=new DatagramPacket(data,data.length);
                while(true){
                    Date  date=new Date();
                    receive.receive(receivepacket);
                    String sendIP=receivepacket.getAddress().toString();
                    String packer=new String(receivepacket.getData(),0,receivepacket.getLength());
                    StringBuffer sbuf=new StringBuffer("");
                    sbuf.append("\n"+"【"+sendIP+"】"+((int)date.getMonth()+1)+"月"+date.getDate()+"日   "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");
                    //sb.append(packer);
                    showMsg.append(sbuf+packer);
                    sbuf.delete(0, sbuf.length());
                    //receivepacket=new DatagramPacket(data,data.length);

                }
            }catch(Exception e){

            }

        }else if(Thread.currentThread()==thread1){//私聊
            try{
                DatagramSocket receive=new DatagramSocket(1990);
                byte[] data=new byte[1024*4];
                DatagramPacket receivepacket=new DatagramPacket(data,data.length);
                while(true){
                    Date  date=new Date();
                    receive.receive(receivepacket);
                    String sendIP=receivepacket.getAddress().toString();
                    String packer=new String(receivepacket.getData(),0,receivepacket.getLength());
                    StringBuffer s=new StringBuffer();
                    s.append("\n"+"【"+sendIP+"】"+((int)date.getMonth()+1)+"月"+date.getDate()+"日   "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");
                    //sb.append(packer);
                    showMsg.append(s+packer);
                    s.delete(0,s.length());
                    //receivepacket=new DatagramPacket(data,data.length);

                }
            }catch(Exception e){

            }
        }
    }
    public boolean isIPAddress(String str){
        String test[]=str.split("//.");
        int temp=0;
        int length=test.length;
        if(length!=4){
            return false;
        }
        for(int i=0;i<test.length;i++){
            temp=Integer.parseInt(test[i]);
            if(temp>255 || temp<0){
                return false;
            }
        }
        return true;
    }
}
