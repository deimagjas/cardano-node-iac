package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.s3.Bucket;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloCdkStack extends Stack {

    private static final Logger LOGGER;
    private static Vpc cardanoVpc;

    static {
        LOGGER = Logger.getLogger(HelloCdkStack.class.getName());
    }
   
    public HelloCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public HelloCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        cardanoVpc = this.configureCardanoNetwork();

    }

    private void cardanoArquitectureBucket () {
        Bucket.Builder.create(this, "CardanoNFTBucket")
                .versioned(true)
                .removalPolicy(RemovalPolicy.DESTROY)
                .autoDeleteObjects(true)
                .build();
    }

    private Vpc configureCardanoNetwork () {

        String CIDR="10.0.0.0/24";

        Vpc cardanoVpc = Vpc.Builder.create(this, "cardanoVpc")
                .cidr(CIDR)
                .enableDnsHostnames(false)
                .maxAzs(1)
                .vpnGateway(false)
                .build();

        LOGGER.log(Level.INFO, "se creó vpc: ", cardanoVpc.getVpcId());

        return cardanoVpc;
    }
}
