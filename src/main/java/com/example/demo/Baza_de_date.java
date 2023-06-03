package com.example.demo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Baza_de_date {
    public void execute_query(String queries)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(queries);

            //pentru numele coloanelor
            ResultSetMetaData rmd=rs.getMetaData();

            //pentru instructiuni;
            for(int i=1;i<=rmd.getColumnCount();i++)
            {
                System.out.print(rmd.getColumnName(i)+" ");
            }
            System.out.println();

            while(rs.next())
            {
                String S="";
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
                {
                    S+=rs.getObject(i)+" ";
                }
                System.out.println(S);
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}


    }

    public static Utilizatori login(String utilizator,String parola)
    {
        Utilizatori utilizatori=null;
        List<Utilizatori>temp=new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from user_ga1 where activ=1 order by id_user");
            //pentru instructiuni;
            while(rs.next())
            {
                if(rs.getString("alias").equals(utilizator)&& rs.getString("parola").equals(parola))
                {
                    int id_user=rs.getInt("id_user");
                    int tip_user=rs.getInt("tip_user");
                    boolean activ_user=(rs.getInt("activ")==1)?true:false;
                    String alias=rs.getString("alias");
                    String parola1=rs.getString("parola");
                    utilizatori=new Utilizatori(id_user,tip_user,activ_user,alias,parola1);
                }
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return utilizatori;
    }
    public static List<Utilizatori>get_lista_de_user()
    {
        List<Utilizatori>temp=new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from user_ga1 order by id_user");
            //pentru instructiuni;
            while(rs.next())
            {
                int id_user=rs.getInt("id_user");
                int tip_user=rs.getInt("tip_user");
                boolean activ_user=(rs.getInt("activ")==1)?true:false;
                String alias=rs.getString("alias");
                String parola=rs.getString("parola");
                temp.add(new Utilizatori(id_user,tip_user,activ_user,alias,parola));
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return temp;
    }
    public static int get_user_lenght()
    {
        int i=0;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from user_ga1 order by id_user");
            //pentru instructiuni;
            while(rs.next())
            {
                i=rs.getInt(1);
            }
            i++;
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return i;
    }

    public static void update_user(Utilizatori u)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("update user_ga1 set tip_user=?,activ=?,alias=?,parola=? where id_user=?");
            stm.setInt(1,u.tip_user);
            stm.setInt(2,u.isActive());
            stm.setString(3,u.alias);
            stm.setString(4, u.parola);
            stm.setInt(5,u.id_user);
            stm.executeUpdate();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static void delete_user(int id)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("delete from user_ga1 where id_user=?");
            stm.setInt(1,id);
            stm.executeUpdate();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static void add_user(Utilizatori u)
    {
        u.id_user=get_user_lenght();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("insert into user_ga1 values(?,?,?,?,?)");
            stm.setInt(1,u.id_user);
            stm.setInt(2,u.tip_user);
            stm.setInt(3,u.isActive());
            stm.setString(4, u.alias);
            stm.setString(5,u.parola);
            stm.executeQuery();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static List<Clienti>get_lista_clienti()
    {
        List<Clienti>temp=new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from clienti_ga order by id_client");
            //pentru instructiuni;
            while(rs.next())
            {
                int id_client=rs.getInt(1);
                String nume=rs.getString(2);
                String prenume=rs.getString(3);
                int tip_abonament=rs.getInt(4);
                int abonament=rs.getInt(5);
                String telefon=rs.getString(6);
                String email=rs.getString(7);
                int nr_intrari=rs.getInt(8);
                Date data_crearii=rs.getDate(9);
                Date data_stergerii=rs.getDate(10);
                int activ=rs.getInt(11);
                temp.add(new Clienti(id_client,nume,prenume,tip_abonament,abonament,telefon,email,nr_intrari,data_crearii,data_stergerii,activ));

            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return temp;
    }

    public static Utilizatori get_user(int id)
    {
        Utilizatori temp=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from user_ga1 where id_user='"+id+"'order by id_user");
            //pentru instructiuni;
            while(rs.next())
            {
                int id_user=rs.getInt("id_user");
                int tip_user=rs.getInt("tip_user");
                boolean activ_user=(rs.getInt("activ")==1)?true:false;
                String alias=rs.getString("alias");
                String parola=rs.getString("parola");
                temp=new Utilizatori(id_user,tip_user,activ_user,alias,parola);
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return temp;
    }

    public static int get_Clienti_lenght()
    {
        int i=0;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select id_client from clienti_ga order by id_client");
            //pentru instructiuni;
            while(rs.next())
            {
                i=rs.getInt(1);
            }
            i++;
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return i;
    }

    public static Clienti get_Clienti(int id)
    {
        Clienti temp;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from clienti_ga order by id_client");
            //pentru instructiuni;
            while(rs.next())
            {
                int id_client=rs.getInt(1);
                String nume=rs.getString(2);
                String prenume=rs.getString(3);
                int tip_abonament=rs.getInt(4);
                int abonament=rs.getInt(5);
                String telefon=rs.getString(6);
                String email=rs.getString(7);
                int nr_intrari=rs.getInt(8);
                Date data_crearii=rs.getDate(9);
                Date data_stergerii=rs.getDate(10);
                int intrari=rs.getInt(11);
                if(id_client==id)
                {
                    return new Clienti(id_client,nume,prenume,tip_abonament,abonament,telefon,email,nr_intrari,data_crearii,data_stergerii,intrari);
                }

            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return null;
    }

    public static void add_client(Clienti c)
    {
        c.id_client=get_Clienti_lenght();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("insert into clienti_ga values(?,?,?,?,?,?,?,?,?,?,?)");
            stm.setInt(1,c.id_client);
            stm.setString(2,c.nume);
            stm.setString(3,c.prenume);
            stm.setInt(4,c.tip_abonament);
            stm.setInt(5,c.abonament);
            stm.setString(6,c.telefon);
            stm.setString(7,c.email);
            stm.setInt(8,c.nr_intrari);
            Date utilDate=new Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            stm.setDate(9, sqlDate);
            stm.setDate(10,null);
            stm.setInt(11,c.activ);
            stm.executeQuery();

            stm=con.prepareStatement("commit");
            stm.executeQuery();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static void update_client(Clienti c)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("update clienti_ga set nume=?,prenume=?,tip_abonament=?,abonament=?,telefon=?,email=?,nr_intrari=?,activ=? where id_client=?");
            stm.setString(1,c.nume);
            stm.setString(2,c.prenume);
            stm.setInt(3,c.tip_abonament);
            stm.setInt(4,c.abonament);
            stm.setString(5,c.telefon);
            stm.setString(6,c.email);
            stm.setInt(7,c.nr_intrari);
            stm.setInt(8,c.activ);
            stm.setInt(9,c.id_client);
            stm.executeUpdate();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static void delete_client(int id)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("delete from clienti_ga where id_client=?");
            stm.setInt(1,id);
            stm.executeUpdate();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static List<Sali> get_lista_sali()
    {
        List<Sali>temp=new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select s.*,u.alias from sali_ga s left join user_ga1 u on u.id_user=s.rezervat_id order by rand");
            //pentru instructiuni;
            while(rs.next())
            {
                int id_sala=rs.getInt(1);
                float rand=rs.getFloat(2);
                int rezervat_id=rs.getInt(3);
                int rezervat=rs.getInt(4);
                int ingrijire=rs.getInt(5);
                String den_ocupa=rs.getString(6);
                temp.add(new Sali(id_sala,rand,rezervat_id,rezervat,ingrijire,den_ocupa));
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return temp;
    }

    public static int get_sali_length()
    {
        int i=1;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from sali_ga order by id_sala");
            //pentru instructiuni;
            while(rs.next())
            {
                i++;
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return i;
    }

    public static void add_sali(Sali s)
    {
        s.id_sala=get_sali_length();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("insert into sali_ga values(?,?,?,?,?)");
            stm.setInt(1,s.id_sala);
            stm.setFloat(2,s.rand);
            stm.setInt(3,s.rezervat_id);
            stm.setInt(4,s.rezervat);
            stm.setInt(5,s.ingrijire);
            stm.executeQuery();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static void update_sali(Sali s)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("update sali_ga set rand=?,rezervat_id=?,rezervat=?,ingrijire=? where id_sala=?");
            stm.setFloat(1,s.rand);
            stm.setInt(2,s.rezervat_id);
            stm.setInt(3,s.rezervat);
            stm.setInt(4,s.ingrijire);
            stm.setInt(5,s.id_sala);
            stm.executeUpdate();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static void delete_sali(int id)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("delete from sali_ga where id_sala=?");
            stm.setInt(1,id);
            stm.executeUpdate();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static boolean find_sali(float id)
    {
        int id1=0;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from sali_ga where rand="+id);
            //pentru instructiuni;
            while(rs.next())
            {
                id1++;
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        if(id1>0)
            return true;
        else return false;
    }

    public static List<Intrari>get_lista_intrari()
    {
        List<Intrari>temp=new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select c.id_client,cg.id_antrenor,c.nume,c.prenume,nvl(u.alias,' '),c.abonament,c.tip_abonament,c.nr_intrari from clienti_ga c"+
                    " left join client_antrenor_ga cg on cg.id_client=c.id_client"+
                    " left join user_ga1 u on u.id_user=cg.id_antrenor where data_expirarii is null and c.activ=1 order by c.id_client");
            //pentru instructiuni;
            while(rs.next())
            {
                int id_client=rs.getInt(1);
                int id_antrenor=rs.getInt(2);
                String nume=rs.getString(3);
                String prenume=rs.getString(4);
                String alias=rs.getString(5);
                int abonament=rs.getInt(6);
                int tip_abonament=rs.getInt(7);
                int nr_intrari=rs.getInt(8);
                temp.add(new Intrari(id_client,id_antrenor,nume,prenume,alias,abonament,tip_abonament,nr_intrari));
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return temp;
    }

    public static List<Clienti_antrenor> get_lista_clienti_antrenor()
    {
        List<Clienti_antrenor>temp=new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from client_antrenor_ga");
            //pentru instructiuni;
            while(rs.next())
            {
                int id_client=rs.getInt(1);
                int id_antrenor=rs.getInt(2);
                Date data_crearii=rs.getDate(3);
                Date data_expirarii=rs.getDate(4);
                temp.add(new Clienti_antrenor(id_client,id_antrenor,data_crearii,data_expirarii));
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return temp;
    }

    public static void add_clienti_antrenor(Clienti_antrenor clientiAntrenor)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");
            Date utilDate=new Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            PreparedStatement stm1=con.prepareStatement("update client_antrenor_ga set data_expirarii=? where id_client=?");
            stm1.setInt(2,clientiAntrenor.id_client);
            stm1.setDate(1,sqlDate);
            stm1.executeQuery();
            PreparedStatement stm=con.prepareStatement("insert into client_antrenor_ga values(?,?,?,?)");
            stm.setInt(1,clientiAntrenor.id_client);
            stm.setInt(2,clientiAntrenor.id_antrenor);
            stm.setDate(3,sqlDate);
            stm.setDate(4,null);
            stm.executeQuery();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static void delete_clienti_antrenor(int id)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("delete from client_antrenor_ga where id_client=?");
            stm.setInt(1,id);
            stm.executeUpdate();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public static void delete_antrenor_clienti(int id)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@80.96.123.131:1521:oracle09","hr","oracletest");

            PreparedStatement stm=con.prepareStatement("delete from client_antrenor_ga where id_antrenor=?");
            stm.setInt(1,id);
            stm.executeUpdate();
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }


    public static void main(String[] args) {
        //add_client(new Clienti("ana1","maria",2,1,"0542424","dada@gmail.com",3));
        //update_client(new Clienti(1,"ana1","maria",2,1,"042424","dadadda@gmail.com",3));
        //add_sali(new Sali(1,2,1,1));
        add_client(new Clienti("ana1","maria",2,1,"0542424","dada@gmail.com",3,1));
        System.out.println(get_lista_clienti());
        System.out.println(login("asas123","dada"));
    }
}

