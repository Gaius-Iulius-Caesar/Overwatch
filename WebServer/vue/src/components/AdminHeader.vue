<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1;font-size: 20px">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px " @click="collapse"></span>
      <el-breadcrumb separator="/" style="display: inline-block;margin-left: 10px">
        <el-breadcrumb-item :to="'/manage'">管理后台</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
      <div style="display: inline-block">
        <span>管理员</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none">配置文件</span>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none">设置</span>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>


  </div>
</template>

<script>
export default {
  name: "AdminHeader",
  props: {
    collapseBtnClass: String,
  },
  data() {
    return {
      form: {},

    }
  },
  created() {

  },
  methods: {
    collapse() {
      this.$parent.$parent.$parent.$parent.collapse()  // 通过4个 $parent 找到父组件，从而调用其折叠方法
      // this.$emit("asideCollapse")
    },

    gotoManageHome() {
      this.$router.push('/manage/managehome')
    },
    logout() {
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    },
  }
}
</script>

<style scoped>

</style>