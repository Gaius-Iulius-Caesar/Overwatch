<template>

    <div class="console_body">
      <h2>分布式机群监管系统</h2>
      <hr>
      <h3>前端</h3>
      <h4>组件说明</h4>
      <ul>
        <li>Spring boot 实现路由控制</li>
        <li>Thymeleaf 模板引擎作为视图</li>
        <li>Bootsrtap 后台管理UI模板</li>
        <li>Echarts 实现图表展示</li>
        <li>Ajax 实现数据心跳以及实时更新数据</li>
      </ul>
      <hr>
      <h3>后端</h3>
      <h4>组件依赖</h4>
      <ul>
        <li>使用 springboot 实现 restful 接口开发</li>
        <li>使用 netty 实现连接的良好扩展性</li>
        <li>使用 hibernate 实现数据库快速开发</li>
      </ul>
    </div>

</template>

<script>
export default {
  name: "AdminConsole"
}
</script>

<style scoped>
.console_body hr{
  margin: 20px 0;
  border: 0;
  border-top: 1px solid #eee;
}

.console_body h2 {
  width: 50%;
  border-bottom: 1px solid #eee;
  font-weight: normal;
}
.console_body h3{
  font-weight: normal;
}
.console_body h4{
  font-weight: normal;
}
.console_body ul{
  margin: 0 0 10px 25px;
}
</style>