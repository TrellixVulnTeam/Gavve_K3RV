import { createStore } from "vuex";

export default createStore({
  state: {
    bannerData:[{id:123123}]
  },
  mutations: {
    REMOVE_BANNER(state, index){
      state.bannerData.splice(index,1);
    }
  },
  actions: {},
  modules: {},
});
