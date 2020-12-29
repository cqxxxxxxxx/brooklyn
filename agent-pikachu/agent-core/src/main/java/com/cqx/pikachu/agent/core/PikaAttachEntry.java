package com.cqx.pikachu.agent.core;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class PikaAttachEntry {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        final List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
            System.out.println(virtualMachineDescriptor.id());
        }
        final VirtualMachine attach = VirtualMachine.attach("67838");
        attach.loadAgent("/Users/cqx/Projects/somethingnew/stn-javaagent/target/cqx-javaagent.jar");
    }
}
