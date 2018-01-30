package com.staples.dashboard.app.service;

import org.springframework.stereotype.Service;






import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * The Class LDAPManager.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
@Service
public class LDAPManager {

    /** The OU (organizational unit) to add users to */
    private static final String USERS_OU =
        "ou=People,o=forethought.com";

    /** The OU (organizational unit) to add groups to */
    private static final String GROUPS_OU =
        "ou=Groups,o=forethought.com";

    /** The OU (organizational unit) to add permissions to */
    private static final String PERMISSIONS_OU =
        "ou=Permissions,o=forethought.com";

    /** The default LDAP port */
    private static final int DEFAULT_PORT = 389;

    /** The LDAPManager instance object */
    @SuppressWarnings("rawtypes")
	private static Map instances = new HashMap();

    /** The connection, through a <code>DirContext</code>, to LDAP */
    private DirContext context;

    /** The hostname connected to */
    private String hostname;

    /** The port connected to */
    private int port;

	private List<Map<String, Object>> userDetails;
	
    /**
     * Parametrized constructor.
     * @param String hostname
     * @param String port
     * @param String username
     * @param String password
     * @throws NamingException
     */
	public LDAPManager(String hostname, int port,
                          String username, String password)
        throws NamingException {

        context = getInitialContext(hostname, port, username, password);

        // Only save data if we got connected
        this.hostname = hostname;
        this.port = port;
    }
    
	/**
	 * Default Constuctor.
	 */
	public LDAPManager(){
		
	}
    
	/**
	 * Static method to get instance.
	 * @param String hostname
	 * @param int port
	 * @param String username
	 * @param String password
	 * @return LDAPManager
	 * @throws NamingException
	 */
    @SuppressWarnings("unchecked")
	public static LDAPManager getInstance(String hostname,
                                          int port,
                                          String username,
                                          String password)
        throws NamingException {

        // Construct the key for the supplied information
        String key = new StringBuffer()
            .append(hostname)
            .append(":")
            .append(port)
            .append("|")
            .append((username == null ? "" : username))
            .append("|")
            .append((password == null ? "" : password))
            .toString();

        if (!instances.containsKey(key)) {
            synchronized (LDAPManager.class) {
                if (!instances.containsKey(key)) {
                    LDAPManager instance =
                        new LDAPManager(hostname, port,
                                        username, password);
                    instances.put(key, instance);
                    return instance;
                }
            }
        }

        return (LDAPManager)instances.get(key);
    }

    /**
     * Method used to get initial context.
     * @param String hostname
     * @param int port
     * @param String username
     * @param String password
     * @return DirContext
     * @throws NamingException
     */
    private DirContext getInitialContext(String hostname, int port,
                                         String username, String password)
        throws NamingException {

        String providerURL =
            new StringBuffer("ldap://")
                .append(hostname)
                .append(":")
                .append(port)
                .toString();

        
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, providerURL);

        // Specify SSL
        env.put(Context.SECURITY_PROTOCOL, "ssl");

        // Fill in secuirty/bind variables
        if ((username != null) && (!username.equals(""))) {
        	env.put(Context.SECURITY_AUTHENTICATION, "simple");
        	env.put(Context.SECURITY_PRINCIPAL, username);
        	env.put(Context.SECURITY_CREDENTIALS,
                ((password == null) ? "" : password));
        }

        // Create the initial context
       
        return new InitialDirContext(env);
    }
    
    /**
     * Method used to search user.
     * @param String emplId
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    public List<Map<String, Object>> searhUser(String emplId) throws Exception{
    	
    	 String base = "dc=staples,dc=com";
    	 Map<String, Object> map = new HashMap<String, Object>();
			userDetails = new ArrayList<Map<String,Object>>();
    	    SearchControls sc = new SearchControls();
    	    String[] attributeFilter = { "givenName", "mail","sn","uid" };
    	    sc.setReturningAttributes(attributeFilter);
    	    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    	    String filter = "(employeeNumber="+emplId+")";

    	    @SuppressWarnings("rawtypes")
			NamingEnumeration<?> results = context.search(base, filter, sc);
    	    
    	    
    	    while (results.hasMore()) {
    	      SearchResult sr = (SearchResult) results.next();
    	      Attributes attrs = sr.getAttributes();

    	      Attribute attr = attrs.get("sn");
    	      
    	      map.put("LAST_NAME", attr.get());
    	      attr = attrs.get("givenName");
    	      map.put("FIRST_NAME", attr.get());
    	      Attribute attrmail = attrs.get("mail");
    	      if(attrmail != null&& attrmail.get() !=null){
    	      map.put("EMAIL", attrmail.get());
    	      }
    	      Attribute attrmuid = attrs.get("uid");
    	      if(attrmuid != null&& attrmuid.get() !=null){
    	      map.put("LAN_ID", attrmuid.get());
    	      }

    	    }
    	    context.close();
    	    userDetails.add(map);
    	    return userDetails;
    }
}