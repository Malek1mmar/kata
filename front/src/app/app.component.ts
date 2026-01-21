import {
  Component, inject,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { CartService } from "./shared/services/cart.service";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {Button} from "primeng/button";
import {BadgeModule} from "primeng/badge";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, OverlayPanelModule, Button, BadgeModule],
})
export class AppComponent {
  title = "ALTEN SHOP";
  public readonly cartService = inject(CartService);
}
