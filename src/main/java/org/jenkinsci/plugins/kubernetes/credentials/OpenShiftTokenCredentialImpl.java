package org.jenkinsci.plugins.kubernetes.credentials;

import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import hudson.Extension;
import hudson.util.Secret;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * @author <a href="mailto:andy.block@gmail.com">Andrew Block</a>
 */
public class OpenShiftTokenCredentialImpl extends BaseStandardCredentials implements TokenProducer {

    private final Secret secret;

    @DataBoundConstructor
    public OpenShiftTokenCredentialImpl(CredentialsScope scope, String id, String description, Secret secret) {
        super(scope, id, description);
        this.secret = secret;
    }

    @Override
    public String getToken(String serviceAddress, String caCertData, boolean skipTlsVerify) {
        return secret.getPlainText();
    }

    public Secret getSecret() {
        return secret;
    }

    @Extension
    public static class DescriptorImpl extends BaseStandardCredentialsDescriptor {

        @Override
        public String getDisplayName() {
            return "OpenShift OAuth token";
        }
    }

}
