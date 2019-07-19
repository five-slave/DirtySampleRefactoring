package ac.kr.ajou.dirt2;

import java.util.HashSet;
import java.util.Set;

public class IpAddressMacAddress {

    // single ip, single mac, accountId, ip + mac
    public Set<String> process(PcBangEvent pbe) {
        Set<String> combin = new HashSet<String>();

        // single IP
        if (pbe.getIp() != null) {
            combin.add(pbe.getIp());
        }

        // single mac
        if (pbe.getMac() != null && !pbe.getMac().isEmpty()) {
            String[] macAddresses = pbe.getMac().split(",");
            if (macAddresses.length <= 100) {
                for (int i = 0; i < macAddresses.length; i++) {
                    combin.add(macAddresses[i]);
                }
            } else {
                System.out.println("Mac address too many");
            }
        } else {
            System.out.println("Mac address is wrong");
        }

        // accountId

        if (pbe.getAccountId() != null && !pbe.getAccountId().isEmpty()) {
            if (!pbe.getAccountId().equals("0")) {
                combin.add(pbe.getAccountId());
            } else {
                System.out.println("Account id can't be 0");
            }
        } else {
            System.out.println("Account is is null or empty");
        }

        // ip + mac
        if (pbe.getMac() != null && !pbe.getMac().isEmpty()) {
            if (pbe.getIp() != null && !pbe.getIp().isEmpty()) {
                String[] macAddresses = pbe.getMac().split(",");
                if (macAddresses.length <= 100) {
                    for (int i = 0; i < macAddresses.length; i++) {
                        combin.add(pbe.getIp() + macAddresses[i]);
                    }
                }
            }
        }

        return combin;
    }
}
