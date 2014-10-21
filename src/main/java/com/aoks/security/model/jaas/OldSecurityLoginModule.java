package com.aoks.security.model.jaas;
//package net.kallx.security.model.jaas;
//
//public class SecurityLoginModule {
//	
//	
//	
//	
//	
//	
//	package net.kallx.security.model.jaas;
//
//	import java.util.Map;
//	import java.util.Set;
//
//	import javax.security.auth.Subject;
//	import javax.security.auth.callback.Callback;
//	import javax.security.auth.callback.CallbackHandler;
//	import javax.security.auth.callback.NameCallback;
//	import javax.security.auth.callback.PasswordCallback;
//	import javax.security.auth.callback.UnsupportedCallbackException;
//	import javax.security.auth.login.FailedLoginException;
//	import javax.security.auth.login.LoginException;
//	import javax.security.auth.spi.LoginModule;
//
//	import net.kallx.security.model.user.ApplicationPrincipal;
//	import net.kallx.security.model.user.User;
//	import net.kallx.security.util.EncrypterUtils;
//
//	import org.springframework.stereotype.Repository;
//
//	@Repository
//	public class DatabaseLoginModule implements LoginModule {
//
//	    DatabaseHelperBean bean = new DatabaseHelperBean();
//	    private Subject subject;
//	    private CallbackHandler handler;
//	    private Map<String, ?> sharedState;
//	    private Map<String, ?> options;
//	    private String username;
//	    private char[] password;
//	    // the authentication status
//	    private boolean succeeded = false;
//	    private boolean commitSucceeded = false;
//	    // user
//	    private User user = null;
//	    // configurable option
//	    private boolean debug = false;
//
//	    @Override
//	    public void initialize(Subject subject, CallbackHandler callbackHandler,
//	            Map<String, ?> sharedState, Map<String, ?> options) {
//
//	        this.subject = subject;
//	        this.sharedState = sharedState;
//	        this.handler = callbackHandler;
//	        this.options = options;
//
//	        System.out.println("INICIALIZANDO CONTEXT DE SEGURANÇA");
//
//	    }
//
//	    @Override
//	    public boolean abort() throws LoginException {
//	        // TODO Auto-generated method stub
//	        return false;
//	    }
//
//	    @Override
//	    public boolean login() throws LoginException {
//
//	        Callback[] callbacks = new Callback[2];
//	        callbacks[0] = new NameCallback("username");
//	        callbacks[1] = new PasswordCallback("password", false);
//
//	        try {
//
//	            handler.handle(callbacks);
//
//	            username = ((NameCallback) callbacks[0]).getName();
//	            char[] tmpPassword = ((PasswordCallback) callbacks[1]).getPassword();
//	            if (tmpPassword == null) {
//	                // treat a NULL password as an empty password
//	                tmpPassword = new char[0];
//	            }
//
//	            char[] encrypted = new EncrypterUtils().digest("sha1", tmpPassword).getChar();
//
//	            password = new char[encrypted.length];
//	            System.arraycopy(encrypted, 0, password, 0, encrypted.length);
//	            ((PasswordCallback) callbacks[1]).clearPassword();
//
//	        } catch (java.io.IOException ioe) {
//	            throw new LoginException(ioe.toString());
//	        } catch (UnsupportedCallbackException uce) {
//	            throw new LoginException("Error: " + uce.getCallback().toString()
//	                    + " not available to garner authentication information "
//	                    + "from the user");
//	        }
//
//	        debug = true;
//
//	        // print debugging information
//	        if (debug) {
//	            System.out.println("\t\t[SampleLoginModule] "
//	                    + "user entered user name: " + username);
//	            System.out.print("\t\t[SampleLoginModule] "
//	                    + "user entered password: ");
//	            for (int i = 0; i < password.length; i++) {
//	                System.out.print(password[i]);
//	            }
//	            System.out.println();
//	        }
//
//	        // verify the username/password
//	        boolean usernameCorrect = false;
//	        boolean passwordCorrect = false;
//
//	        System.out.println("ahaah " + bean);
//	        user = bean.getUser(username);
//
//	        if (user != null) {
//	            usernameCorrect = true;
//	        }
//
//	        if (usernameCorrect) {
//
//	            char[] correctPassword = user.getPassword().toCharArray();
//
//	            if (correctPassword.length == password.length) {
//	                int count = 0;
//	                boolean ver = true;
//	                for (char c : correctPassword) {
//	                    if (!(c == password[count])) {
//	                        ver = ver && false;
//	                    }
//	                    count++;
//	                }
//
//	                if (ver) {
//	                    passwordCorrect = true;
//	                }
//	            }
//
//	        }
//
//	        if (usernameCorrect && passwordCorrect) {
//
//	            // authentication succeeded!!!
//	            if (debug) {
//	                System.out.println("\t\t[SampleLoginModule] "
//	                        + "authentication succeeded");
//	            }
//	            succeeded = true;
//	            return true;
//
//	        } else {
//
//	            // authentication failed -- clean out state
//	            if (debug) {
//	                System.out.println("\t\t[SampleLoginModule] "
//	                        + "authentication failed");
//	            }
//	            succeeded = false;
//	            username = null;
//	            for (int i = 0; i < password.length; i++) {
//	                password[i] = ' ';
//	            }
//	            password = null;
//
//	            if (!usernameCorrect) {
//	                throw new FailedLoginException("User Name Incorrect");
//	            } else {
//	                throw new FailedLoginException("Password Incorrect");
//	            }
//	        }
//	    }
//	    
//	    @Override
//	    public boolean commit() throws LoginException {
//
//	        if (succeeded == false) {
//	            return false;
//	        } else {
//	            // add a Principal (authenticated identity)
//	            // to the Subject
//
//	            // assume the user we authenticated is the SamplePrincipal
//
//	            Set<ApplicationPrincipal> principals = user.getPrincipals();
//
//	            subject.getPrincipals().addAll(principals);
//
//	            if (true) {
//	                System.out.println("\t\t[SampleLoginModule] "
//	                        + "added " + ApplicationPrincipal.class.getSimpleName() + " to Subject");
//	            }
//
//	            // in any case, clean out state
//	            username = null;
//	            for (int i = 0; i < password.length; i++) {
//	                password[i] = ' ';
//	            }
//	            password = null;
//
//	            commitSucceeded = true;
//	            return true;
//	        }
//	    }
//
//	    @Override
//	    public boolean logout() throws LoginException {
//	        // TODO Auto-generated method stub
//	        return false;
//	    }
//	}
//
//	
//	
//	
//
//}
