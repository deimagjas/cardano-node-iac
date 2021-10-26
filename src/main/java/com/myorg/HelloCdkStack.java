package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.PublicSubnet;
import software.amazon.awscdk.services.ec2.Subnet;
import software.amazon.awscdk.services.ec2.SubnetConfiguration;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.ec2.SubnetType;

import java.util.ArrayList;
import java.util.List;

public class HelloCdkStack extends Stack {
    public HelloCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public HelloCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        SubnetConfiguration subnetConfiguration = SubnetConfiguration.builder()
                .name("cardanoPublicSubnet")
                .subnetType(SubnetType.PUBLIC)
                .cidrMask(28)
                .build();

        List<SubnetConfiguration> subnetList = new ArrayList<>();
        subnetList.add(subnetConfiguration);

        Vpc cardanoVpc = Vpc.Builder.create(this, "cardanoVpc")
                .cidr("10.0.0.0/26")
                .enableDnsHostnames(false)
                .maxAzs(4)
                .subnetConfiguration(subnetList)
                .vpnGateway(false)
                .build();
    }

    private void cardanoArquitectureBucket () {
        Bucket.Builder.create(this, "CardanoNFTBucket")
                .versioned(true)
                .removalPolicy(RemovalPolicy.DESTROY)
                .autoDeleteObjects(true)
                .build();
    }

    private void configureCardanoNetwork () {

    }
}
