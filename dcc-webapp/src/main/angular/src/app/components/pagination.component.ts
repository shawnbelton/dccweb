/**
 * Created by shawn on 07/07/17.
 */
import {Component, ContentChild, ElementRef, Input, TemplateRef} from "@angular/core";

@Component({
  moduleId: module.id,
  selector: 'app-pagination',
  templateUrl: './../html/pagination/pagination.html'
})
export class PaginationComponent {
  @Input() items: any[];
  @Input() pageSize: number;
  @ContentChild(TemplateRef) itemTemplate: TemplateRef<ElementRef>;

  currentPage: number;

  constructor() {
    this.currentPage = 1;
  }

  getNumberOfPages(): number {
    const realPages: number = this.items.length / this.pageSize;
    let numPages: number = Math.floor(realPages);
    if (realPages !== numPages) {
      numPages++;
    }
    return numPages;
  }

  getPages(): number[] {
    const pages: number[] = [];
    for (let page = 1; page <= this.getNumberOfPages(); page++) {
      pages.push(page);
    }
    return pages;
  }

  incrementPage():  void {
    if (this.currentPage !== this.getNumberOfPages()) {
      this.currentPage++;
    }
  }

  decrementPage(): void {
    if (this.currentPage !== 1) {
      this.currentPage--;
    }
  }

  goToPage(newPage: number): void {
    this.currentPage = newPage;
  }

  pageItems(): any[] {
    const pagedItems: any[] = [];
    const offset: number = ((this.currentPage - 1) * this.pageSize);
    for (let index = 0; index < this.pageSize; index++) {
      if ((offset + index) < this.items.length) {
        pagedItems.push(this.items[(offset + index)]);
      }
    }
    return pagedItems;
  }

  ngAfterContentInit() {
    console.log(this.itemTemplate);
  }
}
