var rootVM = null;

function RoleMngVM() {
  var self = this;

  self.data = ko.observableArray();
  self.click = function(obj) {
    alert(obj);
    var a = self.data().filter(function(el) {
      return el != obj;
    });
    self.data(a);
  };

  //init
  self.init = function() {
    var a = [
      {
        register: "test1",
        reply: "testreply",
        innb: "1",
        registerID: "testID"
      },
      {
        register: "test2",
        reply: "testreply2",
        innb: "2",
        registerID: "testID2"
      }
    ];

    // var a = {
    //   array: [
    //     {
    //       register: "test1",
    //       reply: "testreply",
    //       innb: "1",
    //       registerID: "testID"
    //     },
    //     {
    //       register: "test2",
    //       reply: "testreply2",
    //       innb: "2",
    //       registerID: "testID2"
    //     }
    //   ]
    // };

    self.data(a);
  };
}

$(document).ready(function() {
  rootVM = new RoleMngVM();
  rootVM.init();
  ko.applyBindings(rootVM);
});
