<template>
  <div>
    <div class="tag" v-if="stat">
      <el-card class="box-card">
        <div slot="header" class="d-flex align-items-center">
          <img class="card-icon" src="@/assets/biaoqian.png"/>
          <span>标签</span>
          <el-button class="edit" type="text" v-if="hasLogin" @click="restat">编辑</el-button>
        </div>
        <div class="text item">
          <el-tag type="small" class="tag-item" v-for="(tag,index) in tags" :key="index" @click="totag(tag)">{{ tag.tag }}</el-tag>
        </div>
      </el-card>
    </div>
    <div class="tag2" v-if="!stat">
      <el-card class="box-card">
        <div slot="header" class="d-flex align-items-center">
          <img class="card-icon" src="@/assets/biaoqian.png"/>
          <span>标签</span>
          <el-button class="edit" type="text" @click="finish">完成</el-button>
        </div>
        <el-tag :key="atag.index" type="small" class="tag-item" v-for="atag in atags" closable :disable-transitions="false" @close="handleClose(atag)">
          {{ atag.tag }}
        </el-tag>
        <el-input
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm">
        </el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">新增标签</el-button>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'tag',
  data() {
    return {
      stat: true,
      tags: [],
      atags:[],
      inputVisible: false,
      inputValue: '',
      hasLogin: false
    }
  },
  methods: {
    finish() {
      this.stat = true
      this.$axios.post("/tags/add", this.atags).then(res => {
        this.$alert('操作成功', '提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$router.go(0)
          }
        });
      })
    },
    restat() {
      this.stat = false
      this.atags=this.tags
    },
    totag(tag) {
      this.$router.push({
        name: 'TagBlog',
        query: {tag: tag.tag}
      });
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleClose(atag) {
      this.atags.splice(this.atags.indexOf(atag), 1);
    },
    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.atags.push({tag:inputValue});
        this.inputVisible = false;
        this.inputValue = ''
      }
    }
  },
  created() {
    this.$axios.get('/tags').then(res => {
      this.tags = res.data.data
    })
    if (this.$store.getters.getUser.username) {
      this.hasLogin = true
    }
  }
}
</script>

<style scoped>
.box-card .item:hover {
  color: #409EFF;
  cursor: pointer;
}

.box-card span {
  font-weight: bold;
}

.card-icon {
  width: 20px;
  height: 20px;
  margin-right: 10px;
}

.tag-item {
  margin-right: 10px;
  margin-top: 10px;
}

.edit {
  position: relative;
  top: 3px;
  font-size: 13px;
}
</style>