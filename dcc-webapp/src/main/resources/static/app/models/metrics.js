"use strict";
/**
 * Created by shawn on 10/01/17.
 */
var Metrics = (function () {
    function Metrics() {
    }
    Metrics.reviver = function (key, value) {
        return key === "" ? Metrics.entries() : Metrics.createMetrics(key, value);
    };
    Metrics.entries = function () {
        Metrics.resetArray = true;
        return Metrics.metricsArr;
    };
    Metrics.createMetrics = function (key, value) {
        if (Metrics.resetArray) {
            Metrics.metricsArr = [];
            Metrics.resetArray = false;
        }
        var metrics = new Metrics();
        metrics.name = key;
        metrics.value = value;
        Metrics.metricsArr.push(metrics);
        return metrics;
    };
    Metrics.metricsArr = [];
    Metrics.resetArray = true;
    return Metrics;
}());
exports.Metrics = Metrics;
//# sourceMappingURL=metrics.js.map