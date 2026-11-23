
package kz.onestep.proxypatternproject;

public class Main {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("       PROXY PATTERN PROJECT");
        System.out.println("       LAZY LOADING");
        System.out.println("       ACCESS CONTROL");
        System.out.println("======================================");

        // 1. USER - ашық сурет
        Image userImage = new ProxyImage(
                "nature.png",
                UserRole.USER,
                true
        );

        // 2. USER - жабық сурет
        Image privateImage = new ProxyImage(
                "secret.png",
                UserRole.USER,
                false
        );

        // 3. ADMIN - жабық сурет
        Image adminImage = new ProxyImage(
                "secret.png",
                UserRole.ADMIN,
                false
        );

        // 4. GUEST - ашық сурет
        Image guestImage = new ProxyImage(
                "landscape.png",
                UserRole.GUEST,
                true
        );

        // USER
        System.out.println("\n1. USER ACCESS");
        userImage.display();

        // Қатынауға тыйым салу
        System.out.println("\n2. PRIVATE IMAGE");
        privateImage.display();

        // ADMIN
        System.out.println("\n3. ADMIN ACCESS");
        adminImage.display();

        // GUEST
        System.out.println("\n4. GUEST ACCESS");
        guestImage.display();

        // Қайта сұраныс
        System.out.println("\n5. REPEATED REQUEST");
        userImage.display();

        System.out.println("\n======================================");
        System.out.println("          PROJECT FINISHED!");
        System.out.println("======================================");
    }
}
