规约说明
---

在下面的规约说明中，会以一些特定的符号来对命名标准进行说明。下面对一些常用的符号进行说明：

- *`|`*：表示互斥的参数
- *`[]`*：表示可选参数
- *`<>`*：表示对参数进行引用。需要用实际值替换的参数名称。
- *`...`*：表示允许出现一个或多个参数
- *`()`*：用于分组。将括号里面的内容作为一个整体

通用规约
---

[阿里巴巴Java开发手册](http://www.xingqiji.com/index.php?mod=pdf&path=UU55cE52bkFUSnJpTWJPTGgxSDJmQVpQZ3RGZl9jS05PWWp3Z3o5T3ZEYm1wbFlPQ0ttMkxEblhVMTczYS1Sd0NqMWltbWNBek9Md0V3)

[](@structure)
结构规约
---

我们当前已经采用maven+springboot来构建项目，则项目结构需要符合maven模块结构。下面对模块中的细节进行详细描述。

### 模块命名

每一个maven项目应该如下方式进行命令
```
<parent-module-name>                     # 父模块或项目名
└── <module-prefix>-<module-suffix>      # 模块名前缀-模块名后缀。模块名前缀一般为项目名
```

例如对于一个名为*`webproject`*的web项目，一般可以按如下方式划分模块
```
webproject                # 名为webproject的项目
├── webproject-common     # 项目通用模块。里面为当前项目其余子模块可通用的代码
├── webproject-core       # 项目核心模块。里面为当前项目的核心业务逻辑
└── webproject-admin      # 项目管理模块。web管理相关功能
```
实际需要根据具体的项目进行模块的划分，在划分模块设计时，各模块不能相互引用。可借鉴大型成熟的开源项目，如[dubbo](https://github.com/apache/dubbo)。

### 模块目录

```
src
├──main               # 模块主目录
│    ├──java          # 源文件
│    ├──resources     # 通用资源文件
│    ├──profiles      # 环境相关(dev、test、prod)资源文件
│    ├──filters       # 资源过滤文件（暂不使用）
│    └──webapp        # web应用文件（传统资源目录）
└──test               # 模块测试目录
```

### 源码目录_Java_

下面以一个简易的示例，大致展示了一个Java包的结构：
```
java
└──com.dxh.cat
    ├──admin                         # 与后台管理相关的类目录。和父目录结构保持一致
    │   ├──controller/               # 控制器相关类目录
    │   ├──service/
    │   ├──repository/
    │   ├──dao/
    │   ├──entity/
    │   ├──util/
    │   └──...
    ├──controller                    # 控制器对象
    ├──service                       # 业务逻辑类目录。业务逻辑类返回对象结果，不直接进行FreeMaker或JSON数据封装
    │   ├──CatService.java
    │   └──impl                      # 业务逻辑层接口实现。实现类以ServiceImpl结尾
    │       └──CatServiceImpl.java
    ├──cache                         # 缓存对象类目录
    │   └──CatCache.java
    ├──dao                           # 数据访问类目录。此目录只存放接口，以大写DAO结尾
    │   ├──CatDAO.java
    │   └──impl                      # 数据访问类的具体实现。实现类以DAOImpl结尾
    │       └──CatDAOImpl.java
    ├──entity                        # 实体对象类目录
    │   ├──vo                        # 视图对象。由controller层封装处理，或直接以对象放入FreeMaker的context中，供页面解析展示，或转换为json返回给前端。视情况一般命名以Info、Result等结尾
    │   │   ├──CatList.java
    │   │   ├──CatResult.java
    │   │   └──CatInfo.java
    │   ├──dto                       # 数据传输对象。同应用层之间的数据传输。如controller将请求参数封装为以Param结尾的对象，传入service层调用
    │   │   ├──CatParam.java
    │   │   └──CatRemoveDto.java
    │   ├──bo                        # 业务实体对象，与具体的业务相关
    │   │   ├──Gua64.java
    │   │   └──Gua8.java
    │   └──po                        # 持久化对象，与数据库表对应
    │       ├──Cat.java
    │       └──CatReply.java
    ├──exception                     # 异常对象目录
    │       └──DatabaseException.java
    └──util                          # 工具类目录
        └──UrlUtil.java
```

<span style="color:rgb(178,124,0)">说明：</span>
①&ensp;当*`entity/po`*目录下的实体对象完全可以封装请求参数或作为返回对象时，不用创建*`dto`*和*`vo`*对象。其它实体对象同样  
②&ensp;以上每个目录下面均可根据需求，创建细分的业务目录。如：*`controller/ly`*、*`controller/bz`*等


### 资源目录_resources_

- *__resources__*

```
resources
├──config                  # 配置文件目录
├──i18n                    # 国际化目录
├──data                    # 数据目录，一些较为通用的数据资源文件
├──doc                     # 文档目录
├──script                  # 脚本目录
├──static                  # 静态资源目录
├──templates               # 模板文件目录
├──application.properties  # SpringBoot应用配置文件
└──module.properties       # 模块配置文件，作为application.properties的扩展。
```

<span style="color:rgb(178,124,0);font">说明：</span>所有的二级目录下面均有名为common和module的两个目录

[](@structure-resources-config)
- *__resources/config__*

```
resources/config
├──common
│   └──data                              # 数据目录
│        ├──ipCountry.txt
│        └──myip.txt
├──module
│   ├──module-custom.properties          # 模块自定义配置属性文件
│   ├──jforum                            # jforum相关配置目录。为了适配JForum框架，其web所需的配置
│   │   ├──url-mappping.properties       # 标准文件 / URL映射文件。对应旧文件 urlPattern.propertes
│   │   ├──templates-mapping.properties  # 标准文件 / 模板映射文件。对应旧文件 templatesMapping.properties
│   │   ├──controller-mapping.properties # 标准文件 / 控制器映射文件。对应旧文件 modulesMapping.properties
│   │   └──sql-mapping.sql               # 标准文件 / SQL查询语句映射文件。对应旧文件 generic_queries.sql
│   ├──data                              # 数据目录
│   ├──script                            # 脚本目录
│   └──doc                               # 文档目录
├──logback.xml      # 日志配置文件
├──proxool.xml      # 数据库连接池配置文件
└──urlrewrite.xml   # 数据库连接池配置文件
```

<span style="color:rgb(178,124,0)">说明：</span>
①&ensp;除了*`resources/.../module/`*目录下的所有文件在多模块依赖时不会被覆盖，其它目录下面的文件，则完全遵循*`maven-war-plugin`*模块资源之间的[默认覆盖规则](https://maven.apache.org/plugins/maven-war-plugin/usage.html)  
②&ensp;*`module/jforum`*下面的四个标准映射文件，为JForum特有的映射。对应SpringBoot的映射，不需要配置。

- *__resources/i18n__*

```
# 命名规范
./(common|module-name)/(common|theme)/[platform/](fonts|styles|js|images)[/(language/file)|(file_language)]

# 结构示例
resources/i18n
├──common
│   ├──<file-name>_zh_CN.properties # 简体中文
│   └──<file-name>_en_US.properties # 美式英文
└──module
    └──<file_name>_zh_CN.properties # 模块私有i18n文件
```

- *__resources/static__*

```
# 命名规范
./(common|module-name)/(common|theme)/[platform/](fonts|styles|js|images)[/(language/file)|(file_language)]

# 结构示例
resources/static
├──common
│   └──<lib-name>                    # 通用静态库名称。如：jQuery、layUI、bootstrap、webuploader等
└──module
    ├──common                        # 模块内不同子模块通用静态资源目录
    │   ├──fonts                     # 字体目录
    │   ├──styles                    # 样式目录
    │   ├──images                    # 图片目录
    │   └──js                        # 脚本目录
    └──default                       # {@link common}。此目录为默认主题目录。后续的子孙目录视情况均可创建一个common目录，其作为同一层级中通用目录
        ├──mobile                    # 移动网页平台
        │   └──styles                # 移动网页样式
        │      ├──module.css         # 移动网页默认语言样式（每个模块只有一个核心样式文件）
        │      └──module_zh_CN.css   # 移动网页简体中文样式
        ├──styles                    # 电脑网页样式
        │   └──module.css            # 电脑网页默认语言样式
        └──images                    # 图片目录
            ├──zh_CN                 # 中文相关图片目录
            │    └──btn.png          # 图片
            └──en_US                 # 英文相关图片目录

```

[](@structure-resources-templates)
- *__resources/templates__*

```
# 命名规范
./(common|module-name)/(common|theme)/[platform/](common|view-name)[/language]/file

# 结构示例
resources/templates                  # 页面模板目录
├──common
│   └──widgets                       # 小部件
└──module
    ├──common                        # 模块内不同子模块通用的页面模板
    │   └──widgets                   # 小部件
    └──default                       # 默认主题目录
        ├──mobile                    # 移动网页视图目录
        │   └──home                  # 移动网页业务视图目录
        │       ├──common            # 移动网页业务视图通用目录
        │       ├──zh_CN             # 移动网页业务视图简体中文目录
        │       ├──en_US             # 移动网页业务视图英文目录
        │       └──index.htm         # 移动网页业务视图入口文件
        └──home                      # 电脑网页业务视图目录
            └──index.htm             # 电脑网页业务视图入口文件
```

### 资源目录_profiles_

此目录放置与环境（*`dev`*、*`test`*、*`prod`*）相关的资源文件，多为配置。比如：数据库、redis等在开发环境和生产环境是不一样的。

```
profiles
├──dev                     # 开发环境配置
├──test                    # 测试环境配置
└──prod                    # 生产环境配置
```

每一个具体环境下的目录与*`resources`*保持一致。

### 资源目录_webapp_

此目录为传统的web应用资源目录，需要打包为war并部署到外部容器中单独运行时，需要此目录。其余情况均使用_resources_目录即可。可参考[项目打成jar与war的区别](https://blog.csdn.net/qq_32331073/article/details/81544061)。

```
webapp
├──META-INF
├──WEB-INF
│   ├──config           # {@link main/resources/config}
│   ├──i18n             # {@link main/resources/i18n}
│   ├──...              # {@link main/resources/...}
│   └──web.xml          # web应用的描述文件。对于非SpringBoot项目的外部需要外部部署的war包，则应该会保留此文件
├──static               # {@link main/resources/static}
├──templates            # {@link main/resources/templates}
└──...                  # 其余旧项目目录及文件。jsp不能再jar包中使用，则所有的jsp文件应该防至webapp目录下
```

[](@config)
配置规约
---

### 文件规约

- 【强制】__命名__。资源文件及其目录命名时，采用中划线风格

<span style="color:rgb(83,152,93)">正例：</span>
```
controller-mapping.properties
templates-mapping.properties
module-custom.properties
```

<span style="color:rgb(255,69,82)">反例：</span>
```
controllerMapping.properties
templatesMapping.properties
moduleCustom.properties
```

### 注释规约

- 【强制】__分类__。配置文件注释分为三种注释，在具体情况时正确使用对应的注释

文件注释

```properties
##################################################################
# description: 这里是文件注释
# module-name: xxx
# module-prefix: xxx
# author: 热土公司开发团队, 星期几公司开发团队
# date: 2022/9/13
##################################################################
```

分段注释

```properties
# ===========================
# 这是分段注释
# ===========================
```

单行注释

```properties
# 这里是单行注释
```

- 【强制】__空格__。所有注释都会以`#`开始，注释符号后面和注释内容需要空一格。

<span style="color:rgb(83,152,93)">正例：</span>
```properties
# 这里是空了一格的注释
```

<span style="color:rgb(255,69,82)">反例：</span>
```properties
#这里是未空格的注释
```

### 内容规约

- 【强制】__命名__。属性键值均按照*`(maven模块|项目).分类.(功能模块...).功能项`*方式，以字母小写中划线的方式进行命名。可参考SpringBoot的[application.properties](https://docs.spring.io/spring-boot/docs/2.4.13/reference/html/appendix-application-properties.html#common-application-properties)

<span style="color:rgb(83,152,93)">正例：</span>
```properties
# 以周易模块为例 
# zy-core/src/main/resources/config/module/module-custom.properties
# 占卜悬赏金额
zy.offer.amount.tian-ji-ce.1=50
zy.offer.amount.tian-ji-ce.2=80
zy.offer.amount.tian-ji-ce.3=100
zy.offer.amount.tian-ji-ce.4=200
zy.offer.amount.tian-ji-ce.5=500
zy.offer.amount.tian-ji-ce.6=800
```

<span style="color:rgb(255,69,82)">反例：</span>
```properties
# 占卜悬赏金额
offer.amount.TianJiCe.1=50
offer.amount.TianJiCe.2=80
offer.amount.TianJiCe.3=100
offer.amount.TianJiCe.4=200
offer.amount.TianJiCe.5=500
offer.amount.TianJiCe.6=800
```

- 【强制】__缩进__。配置文件的键值之间不需要留空格

<span style="color:rgb(83,152,93)">正例：</span>
```properties
zy.offer.amount.tian-ji-ce.1=50
```

<span style="color:rgb(255,69,82)">反例：</span>
```properties
zy.offer.amount.tian-ji-ce.1 =50
zy.offer.amount.tian-ji-ce.1= 50
zy.offer.amount.tian-ji-ce.1 = 50
```

- 【强制】__重复键__。配置文件为键值对，不允许出现重复的键值
- 【强制】在配置文件中同种类型的配置文件放在一起

<span style="color:rgb(83,152,93)">正例：</span>所有的支付配置放在一起，并进行分段注释
```properties
# ===========================
# 支付相关配置
# ===========================
# common
zy.alipay.verify.enable=true
# bz
zy.alipay.verify.appId.bz=2021002189628001
zy.alipay.verify.sign-type.bz=RSA2
# ly
zy.alipay.verify.appId.ly=2016091001880521
zy.alipay.verify.sign-type.ly=RSA2
```
<span style="color:rgb(255,69,82)">反例：</span>配置文件散乱放置。如微信支付配置和支付宝支付配置中间夹杂着其它配置内容。

[](@log)
日志规约
---

- 【强制】不直接使用具体的日志系统，直接使用Lombok的*`@Slf4j`*注解。

<span style="color:rgb(83,152,93)">正例：</span>
```java
@Slf4j
public class HelloWorld {
	public void hello() {
		log.debug("debug info");
	}
}
```

<span style="color:rgb(255,69,82)">反例：</span>
```java
public class HelloWorld {
	private static final Logger log = LoggerFactory.getLogger(HelloWorld.class)

	public void hello() {
		log.debug("debug info");
	}
}
```

异常规约
---

- 【推荐】service通过抛出异常来终止非正常的业务逻辑。

<span style="color:rgb(83,152,93)">正例：</span>
```java
if (paymentPrice != actualPrice) {
    Asserts.fail("支付价格与实际价格不一致");
}
```

[](@coding)
编码规约
---

### 文件编码

- 【强制】项目中所有新建的文件统一采用**UTF-8**编码，包括配置文件、Java源文件、静态资源等。

<span style="color:rgb(178,124,0);font">说明：</span>
①&ensp;对于已有的源文件若为GBK，则先[将GBK源码转换为UTF8](http://doc.xingqiji.com/docs/proj-common/optimize-code#optimize_imports#file_encoding_gbk2utf)

②&ensp;项目必须设置为UTF-8编码，如下在IDEA中进行编码设置：
![idea_setting_encoding](/uploads/proj-common/images/m_f451fd7977bc786ec027bc08a0974542_r.png)
- 【强制】去除Java源代码中的多余引用。[引用优化](http://doc.xingqiji.com/docs/proj-common/optimize-code#optimize_imports)
- 【强制】后续开发对于JSON的相关代码，除了一些通用接口强制使用_`net.sf.json`_外，其余均使用Alibaba的*__fastjson__*。（_`cms-base-to-fastjson`_实现了 _qq json_ 对 _fastjson_ 的适配）
- 【强制】*Lombok*不使用*`val`*关键字

### 依赖管理

- 【强制】当前项目所添加依赖基本能满足绝大部分功能，所以在添加尤其如纯粹工具类库依赖前，需要看项目中是否已经有对应的库。
- 【强制】若需要新添加依赖，则需要将版本按功能类别放在项目的_`pom.xml`_对应位置。（具体类别说明参考项目该文件）
- 【推荐】在_`pom.xml`_中添加包依赖时，确定依赖的包其依赖的子包是否完全必要。若不必要，则使用_`exclusions`_排除不需要的包（参考：ffmpeg相关依赖关系）

[](@jforum)
JForum规约
---

### _MVC Controller_

- 【强制】所有控制器Controller需要继承*`com.retu.commons.controller.BaseController`*类或其子类，并按*`Controller`*作为控制器命名的固定后缀。

<span style="color:rgb(83,152,93)">正例：</span>
```java
public class XueYueHuaController extends BaseController {

    @Override
    public void list() {
        // do something
    }
}
```

- 【强制】所有的Ajax请求，不论是APP还是网页均使用*`com.retu.commons.util.net.Result`*类作为其返回数据对象，保证返回按同一的格式返回数据内容。

<span style="color:rgb(83,152,93)">正例：</span>
```java
public class XueYueHuaController extends BaseController {

    private XueYueHuaService xueYueHuaService;

    public XueYueHuaController() {
        this.xueYueHuaService = new XueYueHuaServiceImpl();
    }

    @Override
    public void list() {
        PoemSelectCond searchCond = BeanProcessor.create().toEntity(PoemSelectCond.class);
        // 采用Result封装返回数据
        returnResult(Result.success(xueYueHuaService.listPoems(searchCond)));
    }
}
```

- 【推荐】Controller中按Service的接口调用实例对象方法，可在构造其中创建Service的实例对象。

<span style="color:rgb(83,152,93)">正例：</span>
```java
public class XueYueHuaController extends BaseController {

    // 全局一个接口对实例对象进行引用
    private XueYueHuaService xueYueHuaService;

    public XueYueHuaController() {
        this.xueYueHuaService = new XueYueHuaServiceImpl();
    }

    @Override
    public void list() {
        PoemSelectCond searchCond = BeanProcessor.create().toEntity(PoemSelectCond.class);
        // 调用接口方法
        returnResult(Result.success(xueYueHuaService.listPoems(searchCond)));
    }
}
```

<span style="color:rgb(255,69,82)">反例：</span>
```java
public class XueYueHuaController extends BaseController {

    @Override
    public void list() {
        PoemSelectCond cond = BeanProcessor.create().toEntity(PoemSelectCond.class);
        // 直接调用实现类的方法
        returnResult(Result.success(new XueYueHuaServiceImpl().listPoems(cond)));
    }
}
```

### _MVC Service_

- 【推荐】Service中按DAO的接口对实例对象进行引用，可在构造其中创建DAO的实例对象。

<span style="color:rgb(83,152,93)">正例：</span>
```java
public class XueYueHuaServiceImpl implements XueYueHuaService {

    private XueYueHuaDAO xueYueHuaDAO;

    public XueYueHuaServiceImpl() {
        this.xueYueHuaDAO = new XueYueHuaDAOImpl();
    }

    @Override
    public List<Poem> listPoems(PoemSelectCond cond) {
        return xueYueHuaDAO.list(cond);
    }
}
```

### _MVC DAO_

- 【强制】每一个业务的DAO均需要定义接口，并继承*`BaseInterfaceDAO`*接口。

<span style="color:rgb(178,124,0)">说明：</span>*`BaseInterfaceDAO`*接口中定义了基础的常用的数据库操作方法。

<span style="color:rgb(83,152,93)">正例：</span>
```java
public interface XueYueHuaDAO extends BaseInterfaceDAO {

    /**
     * 通过诗歌集合查询所有相关的诗人对象
     *
     * @param poems 诗歌集合
     * @return 诗人对象集合
     */
    List<Poet> listPoetsByPoems(List<Poem> poems);
}
```

- 【强制】DAO接口的具体实现，需要继承*`com.retu.commons.db.BaseDAO`*对象。

<span style="color:rgb(178,124,0)">说明：</span>*`BaseDAO`*实现了*`BaseInterfaceDAO`*中定义的接口。

### _MVC 接口调用_

- 【强制】不要在除了Controller层外的其它层（如：Service)使用*`SessionFacade.getUserId()`*方法，包括整个*`SessionFacade`*类。

<span style="color:rgb(178,124,0)">说明：</span>因为这与具体的请求会话相关。当没有请求会话（如：自动任务执行）时，一些通用的业务逻辑无法使用。若需要数据通过Controller封装为DTO，传递给对应的业务逻辑。（注意：一些本身处理会话相关的业务逻辑，则依
然需要操作改对象）

- 【强制】不要在除了Controller层外的其它层使用*`JForumExecutionContext.getRequest()`*、*`JForumExecutionContext.getResponse()`*获取与具体请求返回相关的对象。

<span style="color:rgb(178,124,0)">说明：</span>因为这与具体的请求会话相关。