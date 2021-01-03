package com.lwd.uidemo.stickyheader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lwd.uidemo.R;

import java.util.ArrayList;
import java.util.List;

public class StickyHeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header);

        RecyclerView rvHeroes = findViewById(R.id.rv_heroes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvHeroes.setLayoutManager(linearLayoutManager);
        rvHeroes.addItemDecoration(new HeroDecoration(this));
        rvHeroes.setAdapter(new HeroAdapter(getHeroes()));
    }

    private List<Hero> getHeroes() {
        List<Hero> heroes = new ArrayList<>();

        heroes.add(new Hero("影流之镰 凯影", "艾欧尼亚"));
        heroes.add(new Hero("洛与霞", "艾欧尼亚"));
        heroes.add(new Hero("九尾妖狐 阿狸", "艾欧尼亚"));
        heroes.add(new Hero("暗影之拳 阿卡丽", "艾欧尼亚"));
        heroes.add(new Hero("狂暴之心 凯南", "艾欧尼亚"));
        heroes.add(new Hero("暮光之眼 慎", "艾欧尼亚"));
        heroes.add(new Hero("影流之主 劫", "艾欧尼亚"));
        heroes.add(new Hero("刀锋意志 艾瑞莉娅", "艾欧尼亚"));
        heroes.add(new Hero("戏命师 烬", "艾欧尼亚"));
        heroes.add(new Hero("疾风剑豪 亚索", "艾欧尼亚"));
        heroes.add(new Hero("天启者 卡尔玛", "艾欧尼亚"));
        heroes.add(new Hero("盲僧 李青", "艾欧尼亚"));
        heroes.add(new Hero("无极剑圣 易", "艾欧尼亚"));
        heroes.add(new Hero("众星之子 索拉卡", "艾欧尼亚"));
        heroes.add(new Hero("暗黑元首 辛德拉", "艾欧尼亚"));
        heroes.add(new Hero("惩戒之箭 韦鲁斯", "艾欧尼亚"));
        heroes.add(new Hero("齐天大圣 孙悟空", "艾欧尼亚"));

        heroes.add(new Hero("德玛西亚之力 盖伦", "德玛西亚"));
        heroes.add(new Hero("德玛西亚皇子 嘉文四世", "德玛西亚"));
        heroes.add(new Hero("德邦总管 赵信", "德玛西亚"));
        heroes.add(new Hero("光辉女郎 拉克丝", "德玛西亚"));
        heroes.add(new Hero("琴瑟仙女 娑娜", "德玛西亚"));
        heroes.add(new Hero("圣枪游侠 卢锡安", "德玛西亚"));
        heroes.add(new Hero("正义巨像 加里奥", "德玛西亚"));
        heroes.add(new Hero("无双剑姬 菲奥娜", "德玛西亚"));
        heroes.add(new Hero("圣锤之毅 波比", "德玛西亚"));
        heroes.add(new Hero("德玛西亚之翼 奎因", "德玛西亚"));
        heroes.add(new Hero("龙血武姬 希瓦娜", "德玛西亚"));
        heroes.add(new Hero("暗夜猎手 薇恩", "德玛西亚"));

        heroes.add(new Hero("魔蛇之拥 卡西奥佩娅", "诺克萨斯"));
        heroes.add(new Hero("诺克萨斯之手 德莱厄斯", "诺克萨斯"));
        heroes.add(new Hero("荣耀行刑官 德莱文", "诺克萨斯"));
        heroes.add(new Hero("不祥之刃 卡特琳娜", "诺克萨斯"));
        heroes.add(new Hero("放逐之刃 锐雯", "诺克萨斯"));
        heroes.add(new Hero("刀锋之影 泰隆", "诺克萨斯"));
        heroes.add(new Hero("诡术妖姬 乐芙兰", "诺克萨斯"));
        heroes.add(new Hero("暴怒骑士 克烈", "诺克萨斯"));
        heroes.add(new Hero("策士统领 斯维因", "诺克萨斯"));
        heroes.add(new Hero("首领之傲 厄加特", "诺克萨斯"));
        heroes.add(new Hero("亡灵战神 塞恩", "诺克萨斯"));
        heroes.add(new Hero("猩红收割者 弗拉基米尔", "诺克萨斯"));

        heroes.add(new Hero("爆破鬼才 吉格斯", "皮尔特沃夫"));
        heroes.add(new Hero("皮城女警 凯特琳", "皮尔特沃夫"));
        heroes.add(new Hero("未来守护者 杰斯", "皮尔特沃夫"));
        heroes.add(new Hero("皮城执法官 蔚", "皮尔特沃夫"));
        heroes.add(new Hero("大发明家 黑默丁格", "皮尔特沃夫"));
        heroes.add(new Hero("发条魔灵 奥莉安娜", "皮尔特沃夫"));
        heroes.add(new Hero("探险家 伊泽瑞尔", "皮尔特沃夫"));
        heroes.add(new Hero("青钢影 卡蜜尔", "皮尔特沃夫"));

        heroes.add(new Hero("机械公敌 兰博", "班德尔城"));
        heroes.add(new Hero("麦林炮手 崔斯塔娜", "班德尔城"));
        heroes.add(new Hero("仙灵女巫 璐璐", "班德尔城"));
        heroes.add(new Hero("邪恶小法师 维迦", "班德尔城"));
        heroes.add(new Hero("迅捷斥候 提莫", "班德尔城"));
        heroes.add(new Hero("英勇投弹手 库奇", "班德尔城"));

        heroes.add(new Hero("冰晶凤凰 艾尼维亚", "弗雷尔卓德"));
        heroes.add(new Hero("冰霜女巫 丽桑卓", "弗雷尔卓德"));
        heroes.add(new Hero("弗雷尔卓德之心 布隆", "弗雷尔卓德"));
        heroes.add(new Hero("寒冰射手 艾希", "弗雷尔卓德"));
        heroes.add(new Hero("巨魔之王 特朗德尔", "弗雷尔卓德"));
        heroes.add(new Hero("狂战士 奥拉夫", "弗雷尔卓德"));
        heroes.add(new Hero("雷霆咆哮 沃利贝尔", "弗雷尔卓德"));
        heroes.add(new Hero("凛冬之怒 瑟庄妮", "弗雷尔卓德"));
        heroes.add(new Hero("蛮族之王 泰达米尔", "弗雷尔卓德"));
        heroes.add(new Hero("兽灵行者 乌迪尔", "弗雷尔卓德"));
        heroes.add(new Hero("雪人骑士 努努", "弗雷尔卓德"));
        heroes.add(new Hero("山隐之焰 奥恩", "弗雷尔卓德"));

        heroes.add(new Hero("曙光女神 蕾欧娜", "巨神峰"));
        heroes.add(new Hero("皎月女神 黛安娜", "巨神峰"));
        heroes.add(new Hero("战争之王 潘森", "巨神峰"));
        heroes.add(new Hero("瓦罗兰之盾 塔里克", "巨神峰"));
        heroes.add(new Hero("暮光星灵 佐伊", "巨神峰"));
        heroes.add(new Hero("龙王", "巨神峰"));

        heroes.add(new Hero("暴走萝莉 金克丝", "祖安"));
        heroes.add(new Hero("时间刺客 艾克", "祖安"));
        heroes.add(new Hero("祖安之怒 沃里克", "祖安"));
        heroes.add(new Hero("炼金术士 辛吉德", "祖安"));
        heroes.add(new Hero("祖安狂人 蒙多", "祖安"));
        heroes.add(new Hero("蒸汽机器人 布里茨", "祖安"));
        heroes.add(new Hero("瘟疫之源 图奇", "祖安"));
        heroes.add(new Hero("风暴之怒 迦娜", "祖安"));
        heroes.add(new Hero("机械先驱 维克托", "祖安"));
        heroes.add(new Hero("厄加特", "祖安"));
        heroes.add(new Hero("生化魔人 扎克", "祖安"));

        heroes.add(new Hero("战争之影 赫卡里姆", "暗影岛"));
        heroes.add(new Hero("死亡颂唱者 卡尔萨斯", "暗影岛"));
        heroes.add(new Hero("魂锁典狱长 锤石", "暗影岛"));
        heroes.add(new Hero("蜘蛛女皇 伊丽丝", "暗影岛"));
        heroes.add(new Hero("铁铠冥魂 莫德凯撒", "暗影岛"));
        heroes.add(new Hero("寡妇制造者 伊芙琳", "暗影岛"));
        heroes.add(new Hero("复仇之矛 卡莉斯塔", "暗影岛"));
        heroes.add(new Hero("扭曲树精 茂凯", "暗影岛"));
        heroes.add(new Hero("牧魂人 约里克", "暗影岛"));

        heroes.add(new Hero("海洋之灾 普朗克", "比尔吉沃特"));
        heroes.add(new Hero("赏金猎人 厄运小姐", "比尔吉沃特"));
        heroes.add(new Hero("卡牌大师 崔斯特", "比尔吉沃特"));
        heroes.add(new Hero("法外狂徒 格雷福斯", "比尔吉沃特"));
        heroes.add(new Hero("海兽祭祀 俄洛伊", "比尔吉沃特"));
        heroes.add(new Hero("深海泰坦 诺提勒斯", "比尔吉沃特"));
        heroes.add(new Hero("血港鬼影 派克", "比尔吉沃特"));

        heroes.add(new Hero("沙漠皇帝 阿兹尔", "恕瑞玛"));
        heroes.add(new Hero("战争女神 希维尔", "恕瑞玛"));
        heroes.add(new Hero("殇之木乃伊 阿木木", "恕瑞玛"));
        heroes.add(new Hero("荒漠屠夫 雷克顿", "恕瑞玛"));
        heroes.add(new Hero("披甲龙龟 拉莫斯", "恕瑞玛"));
        heroes.add(new Hero("远古巫灵 泽拉斯", "恕瑞玛"));
        heroes.add(new Hero("水晶先锋 斯卡纳", "恕瑞玛"));
        heroes.add(new Hero("沙漠死神 内瑟斯", "恕瑞玛"));
        heroes.add(new Hero("岩雀 塔莉垭", "恕瑞玛"));

        heroes.add(new Hero("虚空恐惧 科加斯", "虚空之地"));
        heroes.add(new Hero("虚空行者 卡萨丁", "虚空之地"));
        heroes.add(new Hero("虚空之女 卡莎", "虚空之地"));
        heroes.add(new Hero("虚空之眼 维克兹", "虚空之地"));
        heroes.add(new Hero("虚空掠夺者 卡兹克", "虚空之地"));
        heroes.add(new Hero("虚空先知 马尔扎哈", "虚空之地"));
        heroes.add(new Hero("深渊巨口 克格莫", "虚空之地"));
        heroes.add(new Hero("虚空遁地兽 雷克塞", "虚空之地"));

        heroes.add(new Hero("暗裔剑魔 亚托克斯", "符文之地"));
        heroes.add(new Hero("傲之追猎者 雷恩加尔", "符文之地"));
        heroes.add(new Hero("潮汐海灵 菲兹", "符文之地"));
        heroes.add(new Hero("翠神 艾翁", "符文之地"));
        heroes.add(new Hero("堕落天使 莫甘娜", "符文之地"));
        heroes.add(new Hero("恶魔小丑 萨科", "符文之地"));
        heroes.add(new Hero("复仇焰魂 布兰德", "符文之地"));
        heroes.add(new Hero("河流之王 塔姆肯奇", "符文之地"));
        heroes.add(new Hero("黑暗之女 安妮", "符文之地"));
        heroes.add(new Hero("唤潮鲛姬 娜美", "符文之地"));
        heroes.add(new Hero("荆棘之兴 婕拉", "符文之地"));
        heroes.add(new Hero("酒桶 古拉加斯", "符文之地"));
        heroes.add(new Hero("狂野女猎手 奈德丽", "符文之地"));
        heroes.add(new Hero("符文法师 瑞兹", "符文之地"));
        heroes.add(new Hero("迷失之牙 纳尔", "符文之地"));
        heroes.add(new Hero("末日使者 费德提克", "符文之地"));
        heroes.add(new Hero("牛头酋长 阿利斯塔", "符文之地"));
        heroes.add(new Hero("熔岩巨兽 墨菲特", "符文之地"));
        heroes.add(new Hero("审判天使 凯尔", "符文之地"));
        heroes.add(new Hero("时光守护者 基兰", "符文之地"));
        heroes.add(new Hero("武器大师 贾克斯", "符文之地"));
        heroes.add(new Hero("星界游神 巴德", "符文之地"));
        heroes.add(new Hero("永恒梦魇 魔腾", "符文之地"));
        heroes.add(new Hero("永猎双子 千珏", "符文之地"));
        heroes.add(new Hero("铸星龙王 奥瑞利安索尔", "符文之地"));

        return heroes;
    }
}