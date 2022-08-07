<template>

    <!--    搜索区域-->
    <form action="/search">
        <van-search
                v-model="search"
                placeholder="请输入搜索关键词"
                @search="onSearch"
                @clear="onClear"
                clear-trigger="always"
        />
    </form>
    <van-tree-select
            v-model:active-id="activeId"
            v-model:main-active-index="activeIndex"
            :items="items"
            style="height: 100%;"
            @click-nav="clickNav"
            @click-item="clickItems"
    >

    </van-tree-select>
    <van-popup v-model:show="isShow"
               closeable
               lazy-render="false"
               transition-appear="true"
    >
        <van-form v-model="form">
            <van-cell-group inset>
                <van-field
                        v-model="itemName"
                        label="项目名称"
                        readonly="true"
                        model-value="itemName"
                        autosize
                        type="textarea"
                />
                <van-field
                        v-model="clinicalSignificance"
                        label="临床意义"
                        readonly="true"
                        model-value="clinicalSignificance"
                        autosize
                        type="textarea"
                />
                <van-field
                        v-model="specimenType"
                        label="标本类型"
                        readonly="true"
                        model-value="specimenType"
                        autosize
                        type="textarea"
                />
                <van-field
                        v-model="specimenCollection"
                        label="标本收集"
                        readonly="true"
                        model-value="specimenCollection"
                        autosize
                        type="textarea"
                />
                <van-field
                        v-model="reportTime"
                        label="报告时间"
                        readonly="true"
                        model-value="reportTime"
                        autosize
                        type="textarea"
                />
                <van-field
                        v-model="referenceRange"
                        label="参考范围"
                        readonly="true"
                        model-value="referenceRange"
                        autosizautosize
                        type="textarea"
                />

            </van-cell-group>
        </van-form>
    </van-popup>
</template>

<script>

import request from "@/utils/request";
import 'amfe-flexible';
import { ref } from 'vue';

export default {
  name: 'Dictionary',
  components: {

  },
  data() {
    return {
        search:'',
        isShow: false,
        currentPage4: 1,
        pageSize: 10,
        total:0,
        dialogVisible:false,
        showDetailsItemId:'',
        tableData: [],
        items: [],
        user:{},
        itemName :'',
        clinicalSignificance :'',
        specimenType :'',
        specimenCollection :'',
        reportTime :'',
        referenceRange :''
    }
  },
  created() {
      request.get("/api/dictionary/search",{
          params: {
              search: this.search
          }
      }).then(res =>{
          this.items =res.data;
      })
  },
  methods:{
    onSearch(){
      request.get("/api/dictionary/search",{
        params: {
            search: this.search
        }
      }).then(res =>{
        this.items =res.data;
      })
    },
      onClear(){
        console.log('clear');
          request.get("/api/dictionary/search",{
              params: {

              }
          }).then(res =>{
              this.items =res.data;
          })
      },
      clickNav(index){
          request.get("/api/dictionary/change",{
              params: {
                  index: index
              }
          }).then(res =>{
              this.items =res.data;
          })
      },
      async clickItems(item){
          this.showDetailsItemId = item.itemId;
          request.get("/api/dictionary/details",{
              params: {
                  itemId: this.showDetailsItemId
              }
          }).then(res =>{
              this.itemName = res.data.itemName;
              this.clinicalSignificance = res.data.clinicalSignificance;
              this.specimenType = res.data.specimenType;
              this.specimenCollection = res.data.specimenCollection;
              this.reportTime = res.data.reportTime;
              this.referenceRange = res.data.referenceRange;
          })
          this.isShow = true;

      },
  },

}


</script>
<style>
    .van-field{
        width: 9rem;
    }
</style>