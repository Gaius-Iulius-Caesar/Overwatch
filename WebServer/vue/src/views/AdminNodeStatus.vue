<template>
  <div class="box">
    <div class="title">
      <h1>客户端节点<small>>>节点实时状态</small></h1>
    </div>
    <hr>
    <div class="table-header">
      客户端节点实时状态
    </div>
    <table cellspacing="0">
      <thead>
      <th><input id="all" type="checkbox" @click="selectAll"></input></th>
      <th>IP</th>
      <th>Name</th>
      <th>Status</th>
      <th>Ops</th>
      </thead>
      <tbody id="tb">
      <tr v-for="item in list" :key="item.id">
        <th><input type="checkbox" @click="ReverseSelectAll"></input></th>
        <th>{{ item.id }}</th>
        <th>{{ item.name }}</th>
        <th>{{ item. os}}</th>
        <th>
<!--          用了iconmoon字体图标-->
          <a href='javascript:'></a>
          <a href='javascript:'></a>
          <a href='javascript:'></a>
        </th>
      </tr>
      </tbody>
    </table>
  </div>
</template>


<script>

import $ from "jquery";
export default {

  name: "AdminNodeStatus",
  data() {
    return {
      list: []
    }
  },
  created(){
    this.request.get("http://localhost:8081/machine/getAllUpToDate").then(res => {
          if (res.code === '200') {
            this.list = $.parseJSON(res.msg)

          }
    })
  },
  methods: {
    selectAll() {
      let all = document.getElementById("all");
      let body = document.getElementById("tb").getElementsByTagName("input");
      for (let i = 0; i < body.length; i++) {
        body[i].checked = all.checked;
      }

    },
    ReverseSelectAll() {
      let all = document.getElementById("all");
      let body = document.getElementById("tb").getElementsByTagName("input");
      let flag = true;
      for (let j = 0; j < body.length; j++) {
        if (body[j].checked === false) {
          flag = false;
          break;
        }

      }
      all.checked = flag;
    }

  }
}


</script>

<style scoped>
.box {
  width: 100%;
  height: 100%;
}

hr {
  margin: 20px 0;
  border: 0;
  border-top: 1px solid #eee;
}

.title h1 {
  font-size: 25px;
}

.title small {
  font-size: 12px;
}

.table-header {
  height: 10%;
  background-color: cadetblue;
  color: #fff;
}

table {
  width: 100%;
  margin: 0 auto;
  border-collapse: collapse;
  text-align: center;
}

td,
th {
  border: 1px solid #333;
}

thead tr {
  height: 40px;
  background-color: #ccc;
}

tbody {
  font-family: 'icomoon';
}

tbody a {
  color: black;
  margin: 0 3px;
}
</style>