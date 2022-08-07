<template>
  <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px)">
    <!--    功能趋于-->
    <div style="margin: 10px 0">
      <div v-if="!showAdd">
        <el-button type="primary" plain>导入</el-button>
        <el-button type="primary" plain>导出</el-button>
      </div>
      <div v-if="showAdd">
        <el-button type="warning" plain @click="add" >新增</el-button>
        <el-button type="primary" plain>导入</el-button>
        <el-button type="primary" plain>导出</el-button>
      </div>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-select v-model="searchProject"
                 placeholder="请选择项目"
                 style="width: 20%"
                 @change="changeProject"
                 clearable
                 @clear="clearProjectList"
      >
        <el-option
                v-for="item in projectList"
                :key="item.projectId"
                :value="item"
                :label="item.projectName">
        </el-option>
      </el-select>
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable @clear="load"></el-input>
      <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="load" plain>查询</el-button>
    </div>
    <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%">
     <!-- <el-table-column
              prop="itemId"
              label="细目ID"
              sortable
              show-overflow-tooltip="true">
      </el-table-column> -->
      <el-table-column
              type=index
              label="序号"
      >
      </el-table-column>
      <el-table-column
              prop="itemName"
              label="细目名称"
              show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
              prop="clinicalSignificance"
              label="临床意义"
              show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
              prop="specimenType"
              label="标本类型"
              show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
              prop="specimenCollection"
              label="标本收集"
              show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
              prop="reportTime"
              label="报告时间"
              show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
              prop="referenceRange"
              label="参考范围"
              show-overflow-tooltip="true">
      </el-table-column>

      <el-table-column
              fixed="right"
              label="操作">

        <template #default="scope">
          <div style="display: flex; align-content: space-between">
            <el-button @click="handleDetails(scope.row)" type="primary">详情</el-button>
            <el-button @click="handleEdit(scope.row)" type="primary">编辑</el-button>
            <el-popconfirm
                    title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
              <template #reference>
                <el-button type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </div>
        </template>
      </el-table-column>

    </el-table>



    <div style="margin: 10px 0">
      <el-pagination
              background
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage4"
              :page-sizes="[5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
      </el-pagination>

      <!--详情弹窗-->
      <el-dialog
              v-model="dialogVisible_details"
              width="80%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="细目名称">
            <el-input v-model="form.itemName" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="临床意义" >
            <el-input v-model="form.clinicalSignificance" type="textarea" :rows="10"></el-input>
          </el-form-item>
          <el-form-item label="标本类型">
            <el-input v-model="form.specimenType" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="标本收集">
            <el-input v-model="form.specimenCollection" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="报告时间">
            <el-input v-model="form.reportTime" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="参考范围">
            <el-input v-model="form.referenceRange" type="textarea" ></el-input>
          </el-form-item>
          <div id="div2">

          </div>
        </el-form>

      </el-dialog>
      <!--编辑弹窗-->
      <el-dialog
              v-model="dialogVisible"
              width="80%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="项目ID" v-show="false" >
            <el-input  v-model="form.projectId" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="细目名称">
            <el-input v-model="form.itemName" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="临床意义" >
            <el-input v-model="form.clinicalSignificance" type="textarea" :rows="10"></el-input>
          </el-form-item>
          <el-form-item label="标本类型">
            <el-input v-model="form.specimenType" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="标本收集">
            <el-input v-model="form.specimenCollection" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="报告时间">
            <el-input v-model="form.reportTime" type="textarea" ></el-input>
          </el-form-item>
          <el-form-item label="参考范围">
            <el-input v-model="form.referenceRange" type="textarea" ></el-input>
          </el-form-item>
          <div id="div1">

          </div>
        </el-form>
        <template #footer v-model="dialogVisible_edit">
          <span class="dialog-footer">
            <el-button @click="dialogVisible_edit = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
        </template>
      </el-dialog>

      <el-dialog
              title="详情"
              v-model="vis"
              width="50%">

        <el-card>
          <div v-html="detail.content" style="min-height: 150px;">

          </div>
        </el-card>
      </el-dialog>

    </div>
  </div>
</template>

<script>


import request from "@/utils/request";
import { ref } from 'vue';

export default {
  name: 'Items',
  components: {

  },
  data() {
    return {
      form: {},
      search:'',
      projectId:'',
      searchProject:'',
      currentPage4: 1,
      pageSize: 10,
      total:0,
      showAdd:false,
      dialogVisible:false,
      dialogVisible_details:false,
      dialogVisible_edit:false,
      tableData: [],
      projectList: [],
      user:{}
    }
  },
  created() {
    this.load();

    let userStr =sessionStorage.getItem("user") || '{}';
    this.user = JSON.parse(userStr);
//请求后端服务器，确认当前登录用户的合法性
    request.get("/api/user/"+this.user.id).then(res=>{
      if (res.code==='0'){
        this.user = res.data;
      }
    });
  },
  methods:{
    load(){
      request.get("/api/item",{
        params: {
          pageNum: this.currentPage4,
          pageSize: this.pageSize,
          search: this.search,
          projectId: this.projectId
        }
      }).then(res =>{
        this.tableData = res.data.records;
        this.total = res.data.total;
      }),
     request.get("/api/projects/getList",{
       params: {

       }
     }).then(res =>{
       this.projectList = res.data;
     })

    },
    add(){
      this.dialogVisible = true;
      this.dialogVisible_edit = true;
      this.form = {};
      this.form.projectId = this.projectId;
      this.$nextTick(()=>{
        if (this.$refs['upload']){
          this.$refs['upload'].clearFiles();//清楚历史图片
        }
      });
    },
    save(){
      console.log(this.form);
      console.log(this.form.projectId);
      if (this.form.id){//更新
        request.post("/api/item/update",this.form).then(res =>{
          console.log(res);
          if (res.code==='0'){
            this.$message({
              type: "success",
              message: "更新成功"
            })
          }else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load();//刷新表格数据
          this.dialogVisible = false;//关闭弹窗
          this.dialogVisible_edit = false;//关闭弹窗
        });
      }else {//新增
        request.post("/api/item",this.form).then(res =>{
          console.log(res);
          if (res.code==='0'){
            this.$message({
              type: "success",
              message: "新增成功"
            })
          }else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load();//刷新表格数据
          this.dialogVisible = false;//关闭弹窗
          this.dialogVisible_edit = false;//关闭弹窗
        });
      }

    },handleDetails(row){
      this.form = JSON.parse((JSON.stringify(row)));
      this.dialogVisible_details = true;
      this.$nextTick(()=>{
        if (this.$refs['upload']){
          this.$refs['upload'].clearFiles();//清楚历史图片
        }
      });
    },
    handleEdit(row){
      this.form = JSON.parse((JSON.stringify(row)));
      this.dialogVisible = true;
      this.dialogVisible_edit = true;
      this.$nextTick(()=>{
        if (this.$refs['upload']){
          this.$refs['upload'].clearFiles();//清楚历史图片
        }
      });
    },
    handleSizeChange(pageSize){//改变当前页面个数
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){//改变当前页码
      this.currentPage4 = pageNum;
      this.load();
    },
    // currentPage4(){
    //
    // },
    handleDelete(id){
      console.log(id);
      request.delete("/api/item/"+id).then(res => {
        if (res.code==='0'){
          this.$message({
            type: "success",
            message: "删除成功"
          })
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()//重新加载
      });
    },
    filesUploadSuccess(res){
      this.form.cover = res.data;
      this.load();
    },
    changeProject(item){
      this.searchProject = item.projectName;
      this.projectId = item.projectId;
      this.showAdd= true;
      request.get("/api/item",{
        params: {
          projectId: item.projectId
        }
      }).then(res =>{
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    clearProjectList(){
      this.showAdd= false;
    },
  },
}
</script>
