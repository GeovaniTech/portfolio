package managedBean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named(MBAppConfigs.MANAGED_BEAN_NAME)
@SessionScoped
public class MBAppConfigs {
	public static final String MANAGED_BEAN_NAME = "MBAppConfigs";
	
	
}
