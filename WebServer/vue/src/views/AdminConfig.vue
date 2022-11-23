<template>
  <div class='box'>
    <form action="" method="post" onsubmit="false">
      <ul>
        <li>
          <label for="ip">IP Address</label>
          <input type="text" id="ip" name = "ip" v-model="client.ip">
        </li>
        <li>
          <label for="name">Client Name</label>
          <input type="text" id="name" name = "client" v-model="client.name">
        </li>
        <li class="last">
          <input type="button"  class = "submit" @click="submitClient" value="提交">
          <input type="reset" class="reset">
        </li>
      </ul>
    </form>
  </div>
</template>

<script>
export default {
  name: "AdminConfig",
  data(){
    return{
      client:{}
    }
  },
  methods:{
    submitClient(){
      this.request.post("http://localhost:8081/machine/addOneClient", this.client).then(res => {
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
li{
  list-style: none;
}
.box {
  width: 100%;
  height: 100%;
}
.box form{
  height: 50%;
}
.box form label{
  display: inline-block;
  float: left;
  width: 40%;
  margin-right: 10px;
  text-align: end;
  vertical-align: center;
}
.box form input{
  width: 25%;
}
.box form ul{
  display: flex;
  height: 75%;
  flex-direction: column;
}
.box form ul li{
  display: flex;
  flex: 1;
  margin: 10px 0;
  align-items: center;
}
.box form ul .last{
  justify-content: center;
  margin: 30px 0;
}

.form-control{
  width: 50%;
}
.box form .submit{
  width: 10%;
  margin-right: 10%;
}
.box form .reset{
  width: 10%;
}
</style>