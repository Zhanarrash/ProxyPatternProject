
package kz.onestep.proxypatternproject;

public class ProxyImage implements Image {

    private RealImage realImage;

    private final String filename;
    private final UserRole role;
    private final boolean isPublic;

    // Конструктор
    public ProxyImage(
            String filename,
            UserRole role,
            boolean isPublic) {

        this.filename = filename;
        this.role = role;
        this.isPublic = isPublic;
    }

    // Қатынасты бақылау
    private boolean hasAccess() {

        if (role == UserRole.ADMIN) {
            return true;
        }

        if (role == UserRole.USER) {
            return isPublic;
        }

        return false;
    }

    // Суретті көрсету
    @Override
    public void display() {

        System.out.println("\n--------------------------");
        System.out.println("Қолданушы: " + role);
        System.out.println("Сурет: " + filename);

        // Қатынасты тексеру
        if (!hasAccess()) {

            System.out.println(
                    "ҚАТЕ: Суретті көруге рұқсат жоқ!"
            );

            return;
        }

        System.out.println(
                "Қатынауға рұқсат берілді!"
        );

        // Lazy Loading
        if (realImage == null) {

            System.out.println(
                    "Lazy Loading іске қосылды..."
            );

            realImage = new RealImage(filename);

        } else {

            System.out.println(
                    "Сурет бұрын жүктелген!"
            );
        }

        // Суретті көрсету
        realImage.display();
    }
}
