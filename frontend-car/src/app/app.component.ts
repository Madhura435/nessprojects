import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cosmos-car-frontend-ness';
  constructor(public dialog: MatDialog) {

  }

  openDialog() {
    this.dialog.open(PageNotFoundComponent);
  }
}
