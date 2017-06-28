/**
 * Created by shawn on 10/01/17.
 */
export class Metrics {

    static metricsArr: Metrics[] = [];
    static resetArray: boolean = true;

    name: string;
    value: string;

    static reviver(key: string, value: any): any {
        return key === "" ? Metrics.entries() : Metrics.createMetrics(key, value);
    }

    static entries(): Metrics[] {
        Metrics.resetArray = true;
        return Metrics.metricsArr;
    }

    static createMetrics(key: string, value: any): Metrics {
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