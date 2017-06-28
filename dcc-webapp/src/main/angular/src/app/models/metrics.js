"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by shawn on 10/01/17.
 */
class Metrics {
    static reviver(key, value) {
        return key === "" ? Metrics.entries() : Metrics.createMetrics(key, value);
    }
    static entries() {
        Metrics.resetArray = true;
        return Metrics.metricsArr;
    }
    static createMetrics(key, value) {
        if (Metrics.resetArray) {
            Metrics.metricsArr = [];
            Metrics.resetArray = false;
        }
        let metrics = new Metrics();
        metrics.name = key;
        metrics.value = value;
        Metrics.metricsArr.push(metrics);
        return metrics;
    }
}
Metrics.metricsArr = [];
Metrics.resetArray = true;
exports.Metrics = Metrics;
//# sourceMappingURL=metrics.js.map