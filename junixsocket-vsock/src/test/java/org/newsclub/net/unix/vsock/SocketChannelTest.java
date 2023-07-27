/*
 * junixsocket
 *
 * Copyright 2009-2023 Christian Kohlschütter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.newsclub.net.unix.vsock;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.newsclub.net.unix.AFSocketCapability;
import org.newsclub.net.unix.AFSocketCapabilityRequirement;
import org.newsclub.net.unix.AFVSOCKSocketAddress;
import org.newsclub.net.unix.AddressUnavailableSocketException;
import org.newsclub.net.unix.InvalidArgumentSocketException;

import com.kohlschutter.annotations.compiletime.SuppressFBWarnings;
import com.kohlschutter.testutil.TestAbortedWithImportantMessageException;
import com.kohlschutter.testutil.TestAbortedWithImportantMessageException.MessageType;

@AFSocketCapabilityRequirement(AFSocketCapability.CAPABILITY_VSOCK)
@SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
public final class SocketChannelTest extends
    org.newsclub.net.unix.SocketChannelTest<AFVSOCKSocketAddress> {

  public SocketChannelTest() {
    super(AFVSOCKAddressSpecifics.INSTANCE);
  }

  @Override
  protected String checkKnownBugFirstAcceptCallNotTerminated() {
    int[] mm = getLinuxMajorMinorVersion();
    if (mm == null) {
      return null;
    } else if (mm[0] >= 6) {
      return null;
    } else if (mm[0] < 5 || mm[1] < 10 /* 5.10 */) {
      // seen in Linux 5.4.17-2136.305.5.3.el8uek.x86_64 on Oracle Cloud
      return AFVSOCKAddressSpecifics.KERNEL_TOO_OLD + ": First accept call did not terminate";
    } else {
      return null;
    }
  }

  @Override
  protected void handleBind(ServerSocketChannel ssc, SocketAddress sa) throws IOException {
    try {
      super.handleBind(ssc, sa);
    } catch (AddressUnavailableSocketException e) {
      throw (TestAbortedWithImportantMessageException) new TestAbortedWithImportantMessageException(
          MessageType.TEST_ABORTED_WITH_ISSUES, "Could not bind AF_VSOCK server socket to " + sa
              + "; check kernel capabilities.").initCause(e);
    }
  }

  @Override
  protected boolean handleConnect(SocketChannel sc, SocketAddress sa) throws IOException {
    try {
      return super.handleConnect(sc, sa);
    } catch (InvalidArgumentSocketException e) {
      throw (TestAbortedWithImportantMessageException) new TestAbortedWithImportantMessageException(
          MessageType.TEST_ABORTED_WITH_ISSUES, "Could not connect AF_VSOCK socket to " + sa
              + "; check kernel capabilities.").initCause(e);
    }
  }
}
