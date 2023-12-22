package JavaCode.OOP.NewTextVersionFightingGame;

import java.util.Random;

public class Role {
    private String name;
    private int blood;
    private char gender;
    private String face;// 随机长相
    private final int initialBlood; // 初始血量

    String[] BoyFaces = { "风流俊雅", "气宇轩昂", "相貌英俊", "五官端正", "相貌平平", "一塌糊涂", "面貌狰狞" };
    String[] GirlFaces = { "美轮美奂", "沉鱼落雁", "亭亭玉立", "身材姣好", "相貌平平", "相貌简陋", "惨不忍睹" };
    // 攻击描述
    String[] AttacksDesc = {
            "%s使用【空明斩】斩出一道剑气抵挡了%s的【无敌鲨嘴炮】,对其减速并造成了伤害",
            "%s使用【神速】向前冲刺,命中了%s,对其造成伤害并获得300点护盾减少50%%冷却",
            "%s使用【一决生死】击中了%s,对其造成伤害且击飞1秒,同时使其所有恢复效果延迟到5秒之后",
            "%s使用【一重势】一段挥动双刀命中了%s,对其造成伤害",
            "%s使用【一重势】二段向前正斩命中了%s,对其造成伤害, 并附带9%%已损失生命值的物理伤害",
            "%s使用【二重势】挥刀向前突进命中了%s,对其造成伤害并附带50%%减速"
    };
    String[] attacksDesc = {
            "%s投掷一枚【河豚手雷】,对%s造成伤害和25%%减速,持续两秒，并获得其视野",
            "%s发射一枚【无敌鲨嘴炮】,可击退身边敌人,命中敌人后造成伤害并附带5%%已损失生命值的法术伤害,但被%s【空明斩】抵挡",
            "%s召唤【河豚飞艇】进行空中支援,击中了%s对其造成伤害并附带30%%减速,持续1秒",
            "%s使用强化普攻命中了%s,对其造成伤害"
    };

    public Role() {
        this.initialBlood = 0; // Initialize the initialBlood field with a default value
    }

    public Role(String name, int blood, char gender) {
        this.name = name;
        this.blood = blood;
        this.gender = gender;
        this.initialBlood = blood; // Initialize the initialBlood field with the value of blood
        // 随即长相
        setFace(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getFace() {
        return face;
    }

    // 设置颜值
    public void setFace(char gender) {
        Random r = new Random();
        if (gender == '男') {
            int index = r.nextInt(BoyFaces.length);
            this.face = BoyFaces[index];
        } else if (gender == '女') {
            int index = r.nextInt(GirlFaces.length);
            this.face = GirlFaces[index];
        } else {
            this.face = "面貌狰狞";
        }
    }

    public int getInitialBlood() {
        return initialBlood;
    }

    // 定义一个方法用于攻击别人
    // 谁攻击谁？
    // Role r1 = new Role()
    // Role r2 = new Role()
    // r1.攻击(r2)
    // 方法的调用者去攻击参数

    public void attack(Role role) {
        Random r = new Random();
        int index = r.nextInt(AttacksDesc.length);
        String KungFu = AttacksDesc[index];

        // 计算造成的伤害，随机生成(1-1000)
        int CauseDamage = r.nextInt(1001) + 1000;
        // 假设roleMaxBlood是敌人的最大生命值
        int RemainBlood;
        if (KungFu.contains("9%%已损失生命值的物理伤害")) {
            // 计算敌人已损失的生命值
            int lostBlood = role.getInitialBlood() - role.getBlood();
            // 计算已损失生命值的法术伤害
            double physicalDamage = lostBlood * 0.09; // 9%的已损失生命值
            // 插入物理伤害值到攻击描述字符串中
            KungFu = KungFu.replaceAll("9%%已损失生命值的物理伤害", String.format("%.2f点物理伤害", physicalDamage));
            // 计算剩余血量,减去物理伤害，再减去伤害值，保留两位小数
            RemainBlood = (int) (role.getBlood() - physicalDamage - CauseDamage);
        }
        // 计算剩余血量
        else {
            RemainBlood = role.getBlood() - CauseDamage;
        }
        // 修改挨揍后的血量
        if (RemainBlood < 0) {
            RemainBlood = 0;
        }
        role.setBlood(RemainBlood);// role.getBlood()

        // 插入伤害值到攻击描述字符串中
        KungFu = KungFu.replaceAll("(?<!物理|法术)伤害", CauseDamage + "点伤害");
        // this表示方法的调用者
        System.out.printf(KungFu, this.getName(), role.getName());
        System.out.print(",");
        System.out.println(role.getName() + "还剩" + RemainBlood + "点血量");

    }

    public void Attack(Role role) {
        Random r = new Random();
        int index = r.nextInt(attacksDesc.length);
        String kungFu = attacksDesc[index];

        // 如果攻击被抵挡，就不计算伤害
        if (!kungFu.contains("抵挡")) {
            // 计算造成的伤害，随机生成(1-1000)
            int CauseDamage = r.nextInt(1000) + 1;
            // 计算剩余血量
            int RemainBlood = role.getBlood() - CauseDamage;
            // 修改挨揍后的血量
            if (RemainBlood < 0) {
                RemainBlood = 0;
            }
            role.setBlood(RemainBlood);

            // 插入伤害值到攻击描述字符串中
            kungFu = kungFu.replaceAll("(?<!物理|法术)伤害", CauseDamage + "点伤害");

            // 假设roleMaxBlood是敌人的最大生命值
            int roleMaxBlood = role.getBlood();

            // 计算敌人已损失的生命值
            int lostBlood = roleMaxBlood - role.getBlood();

            // 计算已损失生命值的法术伤害
            double magicDamage = lostBlood * 0.05; // 5%的已损失生命值

            // 插入法术伤害值到攻击描述字符串中
            kungFu = kungFu.replaceAll("5%%已损失生命值的法术伤害", String.format("%.2f点法术伤害", magicDamage));
        }

        System.out.printf(kungFu, this.getName(), role.getName());
        System.out.print(",");

        // this表示方法的调用者
        System.out.println(role.getName() + "还剩" + role.getBlood() + "点血量");
    }

    public void ShowRoleInfo() {
        System.out.println("姓名：" + getName());
        System.out.println("性别：" + getGender());
        System.out.println("血量：" + getBlood());
        System.out.println("长相：" + getFace());
    }

}
