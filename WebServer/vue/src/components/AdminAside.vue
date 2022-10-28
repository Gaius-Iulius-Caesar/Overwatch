<template>
  <el-menu style="height: 100%;overflow-x: hidden"
           background-color="rgb(48,48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router>
    <div style="height: 60px; line-height: 60px;text-align: center;">
      <img src="../assets/logo.png" alt="" style="width: 20px;position: relative;top: 5px;margin-right: 5px;">
      <b style="color: white" v-show="logoTextShow">分布式集群管理系统</b>
    </div>

    <el-menu-item index="/admin/adminconsole">
      <i class="el-icon-house"></i>
      <template slot="title">
        <span slot="title">控制台</span>
      </template>
    </el-menu-item>

    <el-menu-item index="/admin/adminconfig">
      <i class="el-icon-s-order"></i>
      <template slot="title">
        <span slot="title">管理配置</span>
      </template>
    </el-menu-item>

    <el-submenu index="/admin">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">客户端节点</span>
      </template>
      <el-menu-item index="/admin/adminnodestatus">
        <i class="el-icon-postcard"></i>
        <span slot="title">节点实时状态</span>
      </el-menu-item>
      <el-menu-item index="/admin/adminnoderealtimediagram">
        <i class="el-icon-s-marketing"></i>
        <span slot="title">实时状态图</span>
      </el-menu-item>
      <el-menu-item index="/admin/adminnodehistorydiagram">
        <i class="el-icon-s-marketing"></i>
        <span slot="title">历史状态图</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "AdminAside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
  methods:{

  }
}
</script>

<style scoped>

</style>