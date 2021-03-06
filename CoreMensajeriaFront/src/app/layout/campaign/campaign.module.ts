import { Component, NgModule, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CampaignRoutingModule } from './campaign-routing.module';
import { CampaignComponent } from './campaign.component';
import { PageHeaderModule } from './../../shared';
import { Campaign } from '../../../model/campaign-model';
import { CreateCampaignComponent } from './create-campaign/create-campaign.component';
import { ModifyCampaignComponent } from './modify-campaign/modify-campaign.component';

@NgModule({
  imports: [
    CommonModule,
    CampaignRoutingModule,
    PageHeaderModule, 
    FormsModule
  ],
  declarations: [CampaignComponent, CreateCampaignComponent, ModifyCampaignComponent]
})
export class CampaignModule implements OnInit {  
  
  private campaignList = Array<Campaign>();
  //private toast: Toast;
  private response: any;
  private empty: boolean;
  selectedCampaign: Campaign;

  constructor() { }
 
  ngOnInit() {
  }

  onSelect(campaign: Campaign): void {
    this.selectedCampaign = campaign;
  }
}
