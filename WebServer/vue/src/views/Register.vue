<template>
  <div class="box">
    <div class="register">
      <div class="register_title">注册</div>
      <form action="" method="post" name="reg" class="register_body" onsubmit="false">
        <ul>
          <li>
            <label for="username"><i class="el-icon-user"></i></label>
            <input type="text" name="" id="username" v-model="user.username"></li>
          <li>
            <label for="pwd"><i class="el-icon-lock"></i></label>
            <input type="text" id="pwd" v-model="user.password"></input>
          </li>
          <li>
            <input type="button" value="提交注册" name="" id="" class="sub" @click="register">
          </li>
        </ul>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      user: {}
    }
  },
  methods: {
    register() {
      this.request.post("http://localhost:9090/user/login", this.user).then(res => {
            if (res.code === '200') {
              this.$message.success("ok")
            } else {
              this.$message.error(res.msg)
            }
          }
      )
    }
  }
}

</script>

<style scoped>
@font-face {
  font-family: 'icomoon';
  src: url('../static/fonts/icomoon.eot?ckldyg');
  src: url('../static/fonts/icomoon.eot?ckldyg#iefix') format('embedded-opentype'),
  url('../static/fonts/icomoon.ttf?ckldyg') format('truetype'),
  url('../static/fonts/icomoon.woff?ckldyg') format('woff'),
  url('../static/fonts/icomoon.svg?ckldyg#icomoon') format('svg');
  font-weight: normal;
  font-style: normal;
  font-display: block;
}

li {
  list-style: none;
}

.box {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #fa5a55, #fa994d);
  overflow: hidden;

}

.register {
  display: flex;
  flex-direction: column;
  margin: 200px auto;
  background-color: #fff;
  width: 400px;
  height: 400px;
  padding: 20px;
  border-radius: 10px
}

.register_title {
  margin: 20px 0;
  height: 24px;
  line-height: 24px;
  text-align: center;
  font-weight: bold;
  font-size: 24px;
}

.register_body {
  flex: 1;
}

.register_body ul {
  height: 80%;
  display: flex;
  flex-direction: column;
}

.register_body ul li {
  display: flex;
  justify-content: center;
  align-items: center;
  flex: 1;
  padding: 0 20px;
}

.register_body ul li label {
  margin-right: 10px;
  text-align: right;
  font-family: 'icomoon';
  font-size: 20px;

}

.register_body ul li input {
  flex: 1;
  padding-left: 5px;
  height: 50%;
  border: 1px solid black;
  border-radius: 8px;

}

.register_body ul li .sub {
  flex: none;
  width: 75%;
  background-color: #E6A23C;
}
</style>