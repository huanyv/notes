# Node使用

## Npm

### 常用命令

* `npm config set registry https://registry.npm.taobao.org`设置淘宝镜像
* `npm install -g yarn`
* <https://blog.csdn.net/qq_39441438/article/details/121517210>
* <https://mp.weixin.qq.com/s/uDHDd6GCpJXgECXUmP0D4A>

```
命令	简写	说明
无	无	将模块安装到本地node_modules目录下，但不保存在package.json中。
--save	-S	将模块安装到本地node_modules目录下，同时保存到package.json中的dependencies配置项中，在生产环境下这个包的依赖依然存在。
--sava-dev	-D	将模块安装到本地node_modules目录下，同时保存到package.json中的devDependencies配置项中，仅供开发时使用。
--global	-g	安装的模块为全局模块，如果命令行模块，会直接链接到环境变量中。
```

## Nvm安装指南

* nvm可以方便的切换node版本

### 下载

* GitHub：<https://github.com/coreybutler/nvm-windows/releases>

### 配置

1. 打开nvm安装目录
2. setting.txt
3. 淘宝镜像：<https://www.npmmirror.com/>

```txt
node_mirror: http://npmmirror.com/mirrors/node/
npm_mirror: http://npmmirror.com/mirrors/npm/
```

### 使用

* 执行`nvm --help`查看帮助
* 安装后执行`node -v`和`npm -v`验证

```
# 查看网络可以安装的 node 版本
nvm list available

# 安装 指定版本 (16.5.0)
nvm install 14.18.3

# 查看已安装版本
nvm ls 

# 使用 指定版本
nvm use 16.5.0

# 卸载 指定版本
nvm uninstall 16.5.0
```

## Nrm

* 安装nrm`npm install -g nrm`
* `nrm ls`列出可用源
* `nrm use <registry>`切换
* `nrm add <registry> <url>`添加新源
* `nrm del <registry>`删除源
* `nrm test <registry>`测试访问速度
* `nrm current`显示当前使用的源


















