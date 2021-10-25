package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.PublicSubnet;
import software.amazon.awscdk.services.ec2.Subnet;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.s3.Bucket;

public class HelloCdkStack extends Stack {
    public HelloCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public HelloCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Vpc cardanoVpc = Vpc.Builder.create(this, "cardanoVpc")
                        .cidr("10.0.0.0/16")
                        .build();
        Subnet cardanoPublicsubnet = PublicSubnet.Builder.create(this, "cardanoPublicSubnet")
                                    .availabilityZone("us-east-1a")
                                    .cidrBlock("10.0.1.0/24")
                                    .mapPublicIpOnLaunch(true)
                                    .vpcId("cardanoVpc")
                                    .build();
    }

    private void cardanoArquitectureBucket () {
        Bucket.Builder.create(this, "CardanoNFTBucket")
                .versioned(true)
                .removalPolicy(RemovalPolicy.DESTROY)
                .autoDeleteObjects(true)
                .build();
    }
}
