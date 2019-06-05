package com.alpha;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController  
public class SpringControllerClass {

    @Autowired  
    JdbcTemplate jdbc;    
    
   
    @RequestMapping(value = "/Restservis", method = RequestMethod.GET) 
    public List<Users> tumKullanicilariAl(){  
    	 return jdbc.query("select * from users",new RowMapper<Users>(){  
    	    @Override  
    	    public Users mapRow(ResultSet rs, int rownumber) throws SQLException {  
    	    	Users e=new Users();  
    	        e.setUserId(rs.getInt(1));  
    	        e.setUserName(rs.getString(2));  
    	        e.setUserPassword(rs.getString(3)); 
    	        return e;  
    	    }  
    	  });  
    }  
    
   
    @RequestMapping(value = "/Restservice", method = RequestMethod.GET) 
    public List<Users> kullaniciAl(@RequestParam(value="name", defaultValue="admin") String name){  
    	 return jdbc.query("select * from users where userName='"+name+"'",new RowMapper<Users>(){  
    	    @Override  
    	    public Users mapRow(ResultSet rs, int rownumber) throws SQLException {  
    	    	Users e=new Users();  
    	        e.setUserId(rs.getInt(1));  
    	        e.setUserName(rs.getString(2));  
    	        e.setUserPassword(rs.getString(3)); 
    	        return e;  
    	    }  
    	  });  
    }    
    
    
    @RequestMapping("/kayitekle")
    public String kayitOustur(@RequestParam(value="name") String name, @RequestParam(value="pass") String pass){  
    	
        jdbc.execute("insert into users(userName,userPassword)values('"+name+"','"+pass+"')");  
        return "data inserted Successfully";  
    }
    
   
    @RequestMapping("/guncelle")
    public String kayitGuncelle(@RequestParam(value="name") String name, @RequestParam(value="pass") String pass){  
    	
        jdbc.execute("UPDATE users SET userPassword='" +pass+ "' WHERE userName='" +name+"'" );  
        return "data updated Successfully";  
    }
    
}