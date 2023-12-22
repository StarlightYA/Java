package JavaCode.OOP.NewTextVersionFightingGame;

public class GameTest {
    public static void main(String[] arg) {
        // 创建第一个角色
        Role r1 = new Role("宫本武藏", 10000, '男');
        // 创建第二个角色
        Role r2 = new Role("卤蛋", 9500, '男');
        r2.ShowRoleInfo();
        r1.ShowRoleInfo();
        // 开始格斗，回合制
        while (true) {
            r1.attack(r2);
            // 判断r2血量
            if (r2.getBlood() == 0) {
                System.out.println(r1.getName() + "击杀了" + r2.getName());
                break;
            }
            r2.Attack(r1);
            if (r1.getBlood() == 0) {
                System.out.println(r2.getName() + "击杀了" + r1.getName());
                break;
            }
        }
    }

}
