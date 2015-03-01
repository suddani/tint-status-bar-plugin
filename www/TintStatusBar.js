var cordova = require('cordova');

function TintStatusBar(){
  this.setColor = function(color) {
    cordova.exec(
      function(){
        console.log("Setting Color succesfull");
      }, // success callback function
      function(e){
        console.log("Error: "+e);
      }, // error callback function
      'TintStatusBarPlugin', // mapped to our native Java class called "TintStatusBarPlugin"
      'setStatusBarColor', // with this action name
      [color]
    );
  };
}
cordova.addConstructor(function() {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.tintstatusbar = new TintStatusBar();
  return window.plugins.tintstatusbar;
});
