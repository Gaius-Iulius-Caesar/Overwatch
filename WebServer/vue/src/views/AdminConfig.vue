<template>
  <div class='box'>
    <form action="" method="post">
      <ul>
        <li>
          <label for="ip">IP Address</label>
          <input type="text" id="ip" name = "ip">
        </li>
        <li>
          <label for="name">Client Name</label>
          <input type="text" id="name" name = "client">
        </li>
        <li>
          <label for="Cpu-number">CPU Number</label>
          <select class="form-control" id="Cpu-number">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="4">4</option>
            <option value="8">8</option>
          </select>
        </li>
        <li>
          <label for="OS-select">OS Select</label>
          <select class="form-group" id="OS-select" multiple="multiple">
            <option value="Windows">Windows XP</option>
            <option value="Windows">Windows 10</option>
            <option value="Linux">CentOS 6.5</option>
            <option value="Ubuntu">Ubuntu 16.04</option>
            <option value="MAC">x86_64Mac OS X9.2.1</option>
            <option value="MAC">x86_64Mac OS X10.12.6</option>
          </select>
        </li>
        <li class="last">
          <input type="submit"  class = "submit">
          <input type="reset" class="reset">
        </li>
      </ul>
    </form>
  </div>
</template>

<script>
export default {
  name: "AdminConfig"
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
.box form label{
  display: inline-block;
  float: left;
  width: 25%;
  margin-right: 10px;
  text-align: end;
  vertical-align: center;
}
.box form input{
  width: 25%;
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