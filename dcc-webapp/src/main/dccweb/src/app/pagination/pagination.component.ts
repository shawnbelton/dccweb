import {AfterContentInit, Component, ContentChild, ElementRef, Input, OnInit, TemplateRef} from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit, AfterContentInit {

  @Input() items: any[];
  @Input() pageSize: number;
  @ContentChild(TemplateRef) itemTemplate: TemplateRef<ElementRef>;

  currentPage: number;

  constructor() {
    this.currentPage = 1;
  }

  getNumberOfPages(): number {
    let realPages: number;
    if (this.items == null) {
      realPages = 0;
    } else {
      realPages = this.items.length / this.pageSize;
    }
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
      if (this.items != null) {
        if ((offset + index) < this.items.length) {
          pagedItems.push(this.items[(offset + index)]);
        }
      }
    }
    return pagedItems;
  }

  ngAfterContentInit() {
    console.log(this.itemTemplate);
  }

  ngOnInit() {
  }

}
